package no.hvl.dat110.messaging;

import no.hvl.dat110.TODO;

import java.util.Arrays;

public class Message {

	// the up to 127 bytes of data (payload) that a message can hold
	private byte[] data;

	// construction a Message with the data provided
	public Message(byte[] data) {
		
		// TODO - START
		if (data == null) {
			throw new IllegalArgumentException("Data kan ikke være null.");
		}

		if (data.length > 127) {
			throw new IllegalArgumentException("Data kan ikke være lenger enn 127 bytes.");
		}

		this.data = Arrays.copyOf(data, data.length);

		// TODO - END
	}

	public byte[] getData() {
		return Arrays.copyOf(this.data, this.data.length);
	}

}
