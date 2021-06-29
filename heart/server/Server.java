package heart.server;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import heart.event.*;
import heart.model.*;
import heart.utils.*;

import java.util.ArrayList;

public class Server {

	private static Socket[] clientSockets = new Socket[2];
	private String[] clientNames = new String[2];
	private static ObjectOutputStream[] clientOOStreams = new ObjectOutputStream[2];
	private int nowPlayerNum = 0;
	public ServerEventManager eventManager = new ServerEventManager();

	public void start(int port) {
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
			//boolean serverStart = true;
			int cnt = 0;
			while ( cnt < 2 ) { //Listen
				Socket cs = serverSocket.accept();
				this.makeConnection(cs);
				cnt++;
			}
			System.out.println("Game run start.");
			Game game = new Game(eventManager);
			game.run();
			System.out.println("GGGGGGGGGG");
			serverSocket.close();
		} catch (Exception ex) {
			System.out.println("Server start error on port:" + port);
		}
	}

	private void makeConnection(Socket newcs) {
		System.out.println("Connected to " + newcs.getRemoteSocketAddress());
		for (int i = 0; i < 2; i++) {
			if (clientSockets[i] == null) {
				try {
					ObjectOutputStream oostream = new ObjectOutputStream(newcs.getOutputStream());
					clientSockets[i] = newcs;
					clientOOStreams[i] = oostream;
					clientNames[i] = null;
					Thread t = new Thread(new Server.Listener(newcs));
					t.start();

				} catch (Exception ex) {
					System.out.println("Connected error at " + newcs.getRemoteSocketAddress());
					ex.printStackTrace();
				}
				break;
			}
		}
	}

	private class Listener extends Thread {
		private Socket cs;
		private ObjectInputStream oistream;

		public Listener (Socket newcs) {
			this.cs = newcs;
			try {
				oistream = new ObjectInputStream(cs.getInputStream());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		@Override
		public void run() {
			Message receiveObj;
			try {
				while ((receiveObj = (Message) oistream.readObject()) != null) {
					System.out.println("Object received from " + cs.getRemoteSocketAddress());
					// =====Do something=====
					switch (receiveObj.getType()) {
						case Message.JOIN:
							System.out.println("Client just connect");
							if ( nowPlayerNum >= 2 ) {
								System.out.println("Too More Clients");
							}
							sendMessage(nowPlayerNum, new Message(Message.JOIN, -1, nowPlayerNum));
							nowPlayerNum++;
							break;
						case Message.EVENT:
							eventManager.localPost((Event) receiveObj.getObj());
							break;
					}
				}
			} catch (Exception ex) {
				System.out.println("Receiving Object from client Failed at port " + cs.getRemoteSocketAddress());
				ex.printStackTrace();
			}
		}
	}

	public static synchronized void boardcastMessage(Message message)
	{
		sendMessage(0, message);
		sendMessage(1, message);
	}

	public static synchronized void sendMessage(int i, Message message)
	{
		if (clientSockets[i] != null && clientOOStreams[i] != null) {
			try {
				clientOOStreams[i].writeObject(message);
				clientOOStreams[i].flush();
			} catch (Exception ex) {
				System.out.println("Error in send message to the client at "
						+ clientSockets[i].getRemoteSocketAddress());
				ex.printStackTrace();
			}
		}
		else {
			System.out.println("No Client Connected");
		}
	}

}
