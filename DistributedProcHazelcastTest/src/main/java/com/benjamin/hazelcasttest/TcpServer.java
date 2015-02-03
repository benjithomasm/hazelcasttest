package com.benjamin.hazelcasttest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class TcpServer {
	
	private HashMap<String, String> commandMap = new HashMap<String, String>();	
	private ServerList serverlist;
	
	public TcpServer(HashMap<String, String> commandMap, ServerList serverlist) {
			super();
			this.commandMap = commandMap;
			this.serverlist = serverlist;
	}
	
	public void startServer() throws HBTestException {
		String command;
	    String response;
	    while(true) {
		    try {
		    	for (Server currentServer: serverlist.getServerList()) {
			    	ServerSocket welcomeSocket = null;
			    	try {
			    		welcomeSocket = new ServerSocket(currentServer.getPort());
			    	} catch(BindException be) {
			    		//	System.out.println("Port Bound Try Next");
			    		continue;
			    		//try next
			    	}
			    	while(true) {
			         Socket connectionSocket = welcomeSocket.accept();
			         BufferedReader inFromClient =
			         new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				       DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
				       command = inFromClient.readLine();
				       response = commandMap.get(command) + '\n';
				       outToClient.writeBytes(response);
				    }
		    	}	
		    } catch (Exception e) {
		    	throw new HBTestException(e);
		    }
	    }
	}
	
	protected void setCommandMap(HashMap<String, String> commandMap) {
		this.commandMap = commandMap;
	}
	public ServerList getServerlist() {
		return serverlist;
	}
	protected void setIplist(ServerList serverlist) {
		this.serverlist = serverlist;
	}

}
