import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private Socket[] clientSockets = new Socket[2];
	private String[] clientNames = new String[2];
	private ObjectOutputStream[] clientOOStreams = new ObjectOutputStream[2];
	private int nowPlayerNum = 0;

	public static void main(String[] args) {

		int port = (args.length == 0)? 2021 : Integer.parseInt(args[0]);
		Server server = new Server();
		server.start(port);

	}

	public void start(int port) {
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
			boolean serverStart = true;
			while (serverStart) { //Listen
				Socket cs = serverSocket.accept();
				this.makeConnection(cs);
			}
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
		System.out.println("Server is full: cannot establish a connection with a client at " + newcs.getRemoteSocketAddress());
	}

	private class Listener extends Thread {
		private Socket cs; // socket connection to the client
		private ObjectInputStream oistream; // ObjectInputStream of the client

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
				// waits for messages from the client
				while ((receiveObj = (Message) oistream.readObject()) != null) {
					System.out.println("Object received from " + cs.getRemoteSocketAddress());
					// =====Do something=====
					if ( receiveObj.getType() == Message.JOIN ) {
						if ( nowPlayerNum >= 2 ) {
							System.out.println("Too More Clients");
						}
						sendMessage(nowPlayerNum, new Message(Message.JOIN, -1, nowPlayerNum));
						nowPlayerNum++;
					}
				}
			} catch (Exception ex) {
				System.out.println("Receiving Object from client Failed at port " + cs.getRemoteSocketAddress());
				ex.printStackTrace();
			}
		}
	}

	public synchronized void sendMessage(int i, Message message)
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
	}

}
