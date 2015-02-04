package com.benjamin.hazelcasttest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HTConstants {
	protected static final Map<String,String> commandMap = new HashMap<String,String>();
	protected static final int START_PORT=6788;
	protected static final int CLUSTER_SIZE=2;
	protected static final int MAX_NODES=4;
	protected static final String CLUSTER_NAME="matrix";

	protected static List<String> iplist = new  ArrayList<String>();
	static {
		commandMap.put( "hello", "hey");
		commandMap.put( "whoareyou", CLUSTER_NAME);
		iplist.add("192.168.1.114");
	}	

}
