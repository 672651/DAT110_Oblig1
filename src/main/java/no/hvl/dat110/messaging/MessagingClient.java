package no.hvl.dat110.messaging;


import java.io.IOException;
import java.net.Socket;

import no.hvl.dat110.TODO;

public class MessagingClient {

	// name/IP address of the messaging server
	private String server;

	// server port on which the messaging server is listening
	private int port;
	
	public MessagingClient(String server, int port) {
		this.server = server;
		this.port = port;
	}
	
	// setup of a messaging connection to a messaging server
	public MessageConnection connect () {
		
		// TODO - START
		// connect to messaging server using a TCP socket
		// create and return a corresponding messaging connection

		try {
			// Create a socket connection to the server
			Socket clientSocket = new Socket(server, port);

			// Wrap the socket in a MessageConnection object
			return new MessageConnection(clientSocket);
		} catch (IOException ex) {
			System.out.println("Connection failed: " + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
		
		// TODO - END

	}
}
