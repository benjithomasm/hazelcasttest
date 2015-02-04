package com.benjamin.hazelcasttest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TcpClient {

	private static final String HELLOCMD ="hello";
	public TcpClient() {
		super();
	}
	
	public boolean checkHeartBeat(String address, int port) throws HBTestException {
		  String response;
		  Socket clientSocket=null; 
		  boolean flag=false;
		  try {
			  clientSocket = new Socket(address, port);
			  DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			  BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			  outToServer.writeBytes(HELLOCMD+"\n");
			  response = inFromServer.readLine();
			  flag = response.equals(HTConstants.commandMap.get(HELLOCMD)); // right response for HELLO? means its our server - no one else running @server
			  return flag;
		  } catch (IOException e) { 
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
