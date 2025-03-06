package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCClientStopStub;

public class Controller  {
	
	private static int N = 5;
	
	public static void main (String[] args) {
		
		DisplayStub display;
		SensorStub sensor;
		
		RPCClient displayclient,sensorclient;
		
		System.out.println("Controller starting ...");
				
		// create RPC clients for the system
		displayclient = new RPCClient(Common.DISPLAYHOST,Common.DISPLAYPORT);
		sensorclient = new RPCClient(Common.SENSORHOST,Common.SENSORPORT);
		
		// setup stop methods in the RPC middleware
		RPCClientStopStub stopdisplay = new RPCClientStopStub(displayclient);
		RPCClientStopStub stopsensor = new RPCClientStopStub(sensorclient);
				
		// TODO - START
		
		// create local display and sensor stub objects
		// connect to sensor and display RPC servers - using the RPCClients
		// read value from sensor using RPC and write to display using RPC

		display = new DisplayStub(displayclient);
		sensor = new SensorStub(sensorclient);

		// Connect to the sensor and display RPC servers
		displayclient.connect();
		sensorclient.connect();

		// Read value from sensor and write to display
		for (int i = 0; i < N; i++) {
			int sensorValue = sensor.read(); // Read sensor value
			System.out.println("Controller: Read sensor value -> " + sensorValue);
			display.write(String.valueOf(sensorValue)); // Write to display

			try {
				Thread.sleep(1000); // Wait 1 second between readings
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// TODO - END
		
		stopdisplay.stop();
		stopsensor.stop();
	
		displayclient.disconnect();
		sensorclient.disconnect();
		
		System.out.println("Controller stopping ...");
		
	}
}
