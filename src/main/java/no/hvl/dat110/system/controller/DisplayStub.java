package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.*;

public class DisplayStub extends RPCLocalStub {

	public DisplayStub(RPCClient rpcclient) {
		super(rpcclient);
	}
	
	public void write (String message) {
		
		// TODO - START
		
		// implement marshalling, call and unmarshalling for write RPC method

		byte[] request = RPCUtils.marshallString(message);

		// Send request to server
		byte[] response = rpcclient.call((byte) Common.WRITE_RPCID, request);

		// Unmarshall response (no return value needed)
		RPCUtils.unmarshallVoid(response);
		
		// TODO - END
		
	}
}
