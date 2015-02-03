package com.benjamin.hazelcasttest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

public class TcpClient {
	
	private static final String HELLOCMD ="hello";
	
	public TcpClient(HashMap<String, String> commandMap, ServerList serverlist) {
		super();
		this.commandMap = commandMap;
		this.serverlist = serverlist;
	}
	
	protected void setCommandMap(HashMap<String, String> commandMap) {
		this.commandMap = commandMap;
	}
	
	protected ServerList getServerlist() {
		return serverlist;
	}
	
	protected void setIplist(ServerList serverlist) {
		this.serverlist = serverlist;
	}
	
	private HashMap<String, String> commandMap = new HashMap<String, String>();	
	private ServerList serverlist;
	
	public boolean checkHeartBeat(int port) throws HBTestException {
		  String response;
		  Socket clientSocket=null; 
		  boolean flag=false;
		  try {
			  clientSocket = new Socket("localhost", port);
			  DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			  BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			  outToServer.writeBytes(HELLOCMD+"\n");
			  response = inFromServer.readLine();
			  flag = response.equals(commandMap.get(HELLOCMD));
			  return flag;
		  } catch (Exception e) { 
			  return flag;
		  }
		  finally { 
			  if (clientSocket!=null) {
				  try {
					  clientSocket.close();
				  } catch  (Exception e) { 
					  throw new HBTestException(e);
				  }
			  }
		  }
	}
	


}
