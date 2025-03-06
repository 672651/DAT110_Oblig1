package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import no.hvl.dat110.TODO;

public class RPCUtils {
	
	public static byte[] encapsulate(byte rpcid, byte[] payload) {
		
		// TODO - START
		
		// Encapsulate the rpcid and payload in a byte array according to the RPC message syntax / format

		if (payload == null) {
			payload = new byte[0]; // Ensure it's not null
		}

		byte[] rpcmsg = new byte[1 + payload.length];

		rpcmsg[0] = rpcid;

		System.arraycopy(payload, 0, rpcmsg, 1, payload.length);

		return rpcmsg;
		
		// TODO - END
	}
	
	public static byte[] decapsulate(byte[] rpcmsg) {
		
		// TODO - START
		
		// Decapsulate the rpcid and payload in a byte array according to the RPC message syntax

		if (rpcmsg == null || rpcmsg.length < 1) {
			throw new IllegalArgumentException("Invalid RPC message format.");
		}

		return Arrays.copyOfRange(rpcmsg, 1, rpcmsg.length);
		
		// TODO - END
		
	}

	// convert String to byte array
	public static byte[] marshallString(String str) {
		
		// TODO - START 

		return str.getBytes(StandardCharsets.UTF_8);
		
		// TODO - END
	}

	// convert byte array to a String
	public static String unmarshallString(byte[] data) {
		
		// TODO - START 

		return new String(data, StandardCharsets.UTF_8);
		
		// TODO - END
	}
	
	public static byte[] marshallVoid() {
		
		// TODO - START 

		return new byte[0];
				
		// TODO - END
		
	}
	
	public static void unmarshallVoid(byte[] data) {
		
		// TODO

		if (data == null) {
			throw new IllegalArgumentException("Invalid void data.");
		}
		
	}

	// convert boolean to a byte array representation
	public static byte[] marshallBoolean(boolean b) {
		
		byte[] encoded = new byte[1];
				
		if (b) {
			encoded[0] = 1;
		} else
		{
			encoded[0] = 0;
		}
		
		return encoded;
	}

	// convert byte array to a boolean representation
	public static boolean unmarshallBoolean(byte[] data) {
		
		return (data[0] > 0);
		
	}

	// integer to byte array representation
	public static byte[] marshallInteger(int x) {
		
		// TODO - START 

		return ByteBuffer.allocate(4).putInt(x).array();
		
		// TODO - END
	}
	
	// byte array representation to integer
	public static int unmarshallInteger(byte[] data) {
		
		// TODO - START 

		if (data == null || data.length != 4) {
			throw new IllegalArgumentException("Invalid integer data.");
		}
		return ByteBuffer.wrap(data).getInt();
		
		// TODO - END
		
	}
}
