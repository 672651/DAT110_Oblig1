package no.hvl.dat110.rpc;

import java.util.HashMap;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.MessageConnection;
import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.messaging.MessagingServer;

public class RPCServer {

	private MessagingServer msgserver;
	private MessageConnection connection;
	
	// hashmap to register RPC methods which are required to extend RPCRemoteImpl
	// the key in the hashmap is the RPC identifier of the method
	private HashMap<Byte,RPCRemoteImpl> services;
	
	public RPCServer(int port) {
		
		this.msgserver = new MessagingServer(port);
		this.services = new HashMap<Byte,RPCRemoteImpl>();
		
	}
	
	public void run() {

	   // TODO - START
	   // - receive a Message containing an RPC request
	   // - extract the identifier for the RPC method to be invoked from the RPC request
	   // - extract the method's parameter by decapsulating using the RPCUtils
	   // - lookup the method to be invoked
	   // - invoke the method and pass the param
	   // - encapsulate return value
	   // - send back the message containing the RPC reply

		RPCRemoteImpl rpcstop = new RPCServerStopImpl(RPCCommon.RPIDSTOP, this);
		register(RPCCommon.RPIDSTOP, rpcstop);

		System.out.println("RPC SERVER RUN - Services: " + services.size());

		connection = msgserver.accept();
		System.out.println("RPC SERVER ACCEPTED");

		boolean stop = false;

		while (!stop) {
			try {

				Message requestMsg = connection.receive();
				if (requestMsg == null) {
					continue;
				}

				byte[] requestData = requestMsg.getData();
				if (requestData.length < 1) {
					continue;
				}

				byte rpcid = requestData[0];

				byte[] params = new byte[requestData.length - 1];
				System.arraycopy(requestData, 1, params, 0, params.length);

				RPCRemoteImpl method = services.get(rpcid);
				byte[] response = (method != null) ? method.invoke(params) : new byte[0];

				byte[] replyData = new byte[response.length + 1];
				replyData[0] = rpcid;
				System.arraycopy(response, 0, replyData, 1, response.length);

				connection.send(new Message(replyData));

				if (rpcid == RPCCommon.RPIDSTOP) {
					stop = true;
				}
			} catch (Exception e) {
				System.out.println("RPC Server Error: " + e.getMessage());
				e.printStackTrace();
			}
		}

		stop();
		   
		   // TODO - END

	}
	
	// used by server side method implementations to register themselves in the RPC server
	public void register(byte rpcid, RPCRemoteImpl impl) {
		services.put(rpcid, impl);
	}
	
	public void stop() {

		if (connection != null) {
			connection.close();
		} else {
			System.out.println("RPCServer.stop - connection was null");
		}
		
		if (msgserver != null) {
			msgserver.stop();
		} else {
			System.out.println("RPCServer.stop - msgserver was null");
		}
		
	}
}
