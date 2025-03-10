package no.hvl.dat110.messaging;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import no.hvl.dat110.TODO;


public class MessageConnection {

	private DataOutputStream outStream; // for writing bytes to the underlying TCP connection
	private DataInputStream inStream; // for reading bytes from the underlying TCP connection
	private Socket socket; // socket for the underlying TCP connection
	
	public MessageConnection(Socket socket) {

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream (socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void send(Message message) {

		// TODO - START
		// encapsulate the data contained in the Message and write to the output stream

		try {
			byte[] data = message.getData();
			int length = data.length;

			if (length > 127) {
				throw new IllegalArgumentException("Melding for lang (max 127 bytes)");
			}

			outStream.writeByte(length);
			outStream.write(data);
			outStream.flush();
		} catch (IOException ex) {
			System.out.println("Send error: " + ex.getMessage());
			ex.printStackTrace();
		}

		// TODO - END

	}

	public Message receive() {

		// TODO - START
		// read a segment from the input stream and decapsulate data into a Message

		try {
			// Read the first byte to determine message length
			int length = inStream.readByte();
			if (length < 0 || length > 127) {
				throw new IOException("Invalid message length received: " + length);
			}

			// Read the message data
			byte[] data = new byte[length];
			inStream.readFully(data); // Ensures we read exactly 'length' bytes

			return new Message(data);
		} catch (IOException ex) {
			System.out.println("Receive error: " + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
		
		// TODO - END
	}

	// close the connection by closing streams and the underlying socket	
	public void close() {

		try {
			
			outStream.close();
			inStream.close();

			socket.close();
			
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}