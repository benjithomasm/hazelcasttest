package com.benjamin.hazelcasttest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer extends Thread {

	public TcpServer() {
			super();
	}
	
	public void startServer() throws HBTestException {
		String command;
	    String response;
		    try {
		    	int port = HTConstants.START_PORT;
		    	for (int i=0;i<HTConstants.MAX_NODES;i++) {
			    	ServerSocket welcomeSocket = null;
			    	try {
			    		welcomeSocket = new ServerSocket(port);
			    	} catch(BindException be) {
			    		port++; //try next port
			    		continue;
			    	}
					System.out.println("Socket is listening for requests@port - "+port);
			    	while(true) {
			         Socket connectionSocket = welcomeSocket.accept();
			         BufferedReader inFromClient =
			         new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				       DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
				       command = inFromClient.readLine();
				       response = HTConstants.commandMap.get(command) + '\n'; //standard ACK's from commandMap
				       outToClient.writeBytes(response);
				    }
		    	}	
		    } catch (IOException e) {
		    	throw new HBTestException(e);
		    }
	}
	
	@Override
	public void run() {
		try {
			startServer();		
		} catch (HBTestException e) {
			e.printStackTrace();
		}
	}
	

}
