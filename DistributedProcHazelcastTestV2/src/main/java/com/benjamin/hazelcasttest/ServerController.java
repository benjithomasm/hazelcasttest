package com.benjamin.hazelcasttest;

import java.util.HashSet;
import java.util.Set;


public class ServerController {
	
	Set <String> activeNodes = new HashSet<String>();
	
	public static void main(String[] args) {
		new ServerController().runServer();
	}

	private void runServer() {
		try {
				Thread thread = new TcpServer();
				thread.start();
				Thread.sleep(100);
				scanForNodes();
				
		} catch(InterruptedException ie) {
				ie.printStackTrace();
		} catch(HBTestException he) {
			he.printStackTrace();
		}
	}

	private void scanForNodes() throws HBTestException {
		boolean wearerunning = false; 

		TcpClient client = new TcpClient();
		for (String nodeip:HTConstants.iplist) {
			int port = HTConstants.START_PORT;
			while (!wearerunning){
			 for (int i=0; i<HTConstants.MAX_NODES; i++) {
				 if (client.checkHeartBeat(nodeip, port)) {
					 activeNodes.add(nodeip+":"+port);
				 if (HTConstants.CLUSTER_SIZE==activeNodes.size()) {
					wearerunning = true;
					break;
				 }	
				 }
				 port++;
			 }
			}
		}
		if (wearerunning) {
			System.out.println("We are running");
		}
	}	

}
