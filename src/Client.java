package src
import java.io.*;
import java.net.Socket;
import java.net.InetAddress;

import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.codehaus.groovy.control.messages.Message;
import src.model.*;
import src.event.*;
import java.util.List;
import java.util.ArrayList;
import src.utils.*;
import src.client.model.*;
import src.client.controller.*;
import src.client.view.*;
import src.client.viewconstant.*;

public class Client  {

	private String playerName = "";
	private static int playerID = -2;
	private String serverIP;
	private int serverPort;
	private ObjectOutputStream oos;
	public EventManager eventManager = new EventManager();

	public static void main(String[] args)
	{
		String IP = (args.length == 0)? "127.0.0.1" : args[0];
		int port = (args.length <= 1)? 2021 : Integer.parseInt(args[1]);
		Client game = new Client();
		game.start(IP, port);
	}

	public void start(String IP, int port)
	{
		serverIP = IP;
		serverPort = port;

		JTextField playerNameField = new JTextField(playerName, 10);
		JPanel playerNamePanel = new JPanel();
		playerNamePanel.add(new JLabel("Type your name here:"));
		playerNamePanel.add(playerNameField);
		JOptionPane.showConfirmDialog(null,
				playerNamePanel,
				"Welcome to HeartStone",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		this.playerName = playerNameField.getText();

		System.out.format("IP: %s Port: %s%n", serverIP, serverPort);
		this.makeConnection();

		GameInfo gameinfo = new GameInfo(eventManager);
		Test test =new Test(eventManager,game);
		test.run();
		System.out.println("GGGGGGGGGG");
	}

	public void makeConnection()
	{
		try {
			Socket serverSocket = new Socket(InetAddress.getByName(serverIP), serverPort);
			oos = new ObjectOutputStream(serverSocket.getOutputStream());

			Thread t = new Thread(new serverListener(serverSocket));
			t.start();

			System.out.println("Connected Success to server at " + serverIP);

			this.sendMessage(new Message(Message.JOIN, playerID));

			Thread.sleep(500);

		} catch(Exception ex) {
			System.out.println("Server no respond.");
		}
	}

	private class serverListener implements Runnable
	{
		private ObjectInputStream oistream;

		private serverListener(Socket socket)
		{
			try{
				oistream = new ObjectInputStream(socket.getInputStream());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		@Override
		public void run()
		{
			Message receiveObj;
			try{
				while ((receiveObj = (Message) oistream.readObject()) != null) {
					// =====Do something=====
					switch (receiveObj.getType()) {
						case Message.JOIN:
							playerID = (Integer) receiveObj.getObj();
							System.out.println("My ID is " + playerID);
							break;
						case Message.EVENT:
							eventManager.localPost((Event) receiveObj.getObj());
							break;
					}
				}
			} catch(Exception ex) {
				System.out.println("Connection lost");
				ex.printStackTrace();
			}
		}
	}
	public static synchronized void sendMessage(Message message)
	{
		try
		{
			System.out.println("Message Sent Success, Type: " + message.getType() );
			oos.writeObject(message);
			oos.flush();
		} catch (Exception ex) {
			System.out.println("Message Sent Failed");
			ex.printStackTrace();
		}
	}

	public static int getPlayerID() { return playerID; }

}
