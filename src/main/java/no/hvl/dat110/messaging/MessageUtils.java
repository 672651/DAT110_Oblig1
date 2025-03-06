package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class MessageUtils {

	public static final int SEGMENTSIZE = 128;

	public static int MESSAGINGPORT = 8080;
	public static String MESSAGINGHOST = "localhost";

	public static byte[] encapsulate(Message message) {

		// TODO - START
		
		// encapulate/encode the payload data of the message and form a segment
		// according to the segment format for the messaging layer

		byte[] data = message.getData();
		int length = data.length;

		if (length > 127) {
			throw new IllegalArgumentException("Message data too long (max 127 bytes)");
		}

		byte[] segment = new byte[SEGMENTSIZE];

		segment[0] = (byte) length;

		System.arraycopy(data, 0, segment, 1, length);

		return segment;
			
		// TODO - END
		
	}

	public static Message decapsulate(byte[] segment) {
		
		// TODO - START
		// decapsulate segment and put received payload data into a message

		int length = segment[0];

		if (length < 0 || length > 127) {
			throw new IllegalArgumentException("Invalid segment length: " + length);
		}

		byte[] data = new byte[length];
		System.arraycopy(segment, 1, data, 0, length);

		return new Message(data);
		
		// TODO - END
		
	}
	
}
