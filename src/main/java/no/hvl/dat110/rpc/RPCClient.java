package no.hvl.dat110.rpc;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.*;

public class RPCClient {

	// underlying messaging client used for RPC communication
	private MessagingClient msgclient;

	// underlying messaging connection used for RPC communication
	private MessageConnection connection;
	
	public RPCClient(String server, int port) {
	
		msgclient = new MessagingClient(server,port);
	}
	
	public void connect() {
		
		// TODO - START
		// connect using the RPC client

		connection = msgclient.connect();
		if (connection == null) {
			throw new IllegalStateException("Failed to connect to the server.");
		}
		
		// TODO - END
	}
	
	public void disconnect() {
		
		// TODO - START
		// disconnect by closing the underlying messaging connection

		if (connection != null) {
			connection.close();
			connection = null;
		}
		
		// TODO - END
	}

	/*
	 Make a remote call om the method on the RPC server by sending an RPC request message and receive an RPC reply message

	 rpcid is the identifier on the server side of the method to be called
	 param is the marshalled parameter of the method to be called
	 */

	public byte[] call(byte rpcid, byte[] param) {
		
		// TODO - START

		/*

		The rpcid and param must be encapsulated according to the RPC message format

		The return value from the RPC call must be decapsulated according to the RPC message format

		*/

		if (connection == null) {
			throw new IllegalStateException("Client is not connected to a server.");
		}

		byte[] request = new byte[param.length + 1];
		request[0] = rpcid; // First byte is the RPC ID
		System.arraycopy(param, 0, request, 1, param.length); // Copy parameters

		connection.send(new Message(request));

		Message response = connection.receive();
		if (response == null) {
			throw new IllegalStateException("No response received from server.");
		}

		byte[] responseData = response.getData();
		if (responseData.length < 1) {
			throw new IllegalStateException("Invalid response received.");
		}

		byte[] returnval = new byte[responseData.length - 1];
		System.arraycopy(responseData, 1, returnval, 0, returnval.length);

		return returnval;
		
		// TODO - END
		
	}

}
