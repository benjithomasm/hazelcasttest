package com.benjamin.hazelcasttest;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ServerList {

	public ServerList(List<String> iplist) {
		super();
		this.iplist = iplist;
		serverList = new ArrayList<Server>(iplist.size());
		
		for (String element : iplist) {
			StringTokenizer stk = new StringTokenizer(element, ":");
			String address = stk.nextToken();
			int port = Integer.parseInt(stk.nextToken());
			serverList.add(new Server(address, port));
		}
	}
	
	List<String> iplist;
	List<Server> serverList;

	public List<Server> getServerList() {
		return serverList;
	}

	public void setServerList(List<Server> serverList) {
		this.serverList = serverList;
	}
	public List<String> getIplist() {
		return iplist;
	}

	public void setIplist(List<String> iplist) {
		this.iplist = iplist;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("IP list contains:\n");
		
		for(Server server: serverList) {
			sb.append(server.getAddress()+":"+server.getPort());
			sb.append(", ");
		}
		return sb.toString();
	}
	
}
