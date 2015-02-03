package com.benjamin.hazelcasttest;

public class TcpServerThreadWrapper extends Thread {
	public TcpServerThreadWrapper(TcpServer tcps) {
		super();
		this.tcps = tcps;
	}

	TcpServer tcps;
	
	@Override
	public void run() {
		try {
			tcps.startServer();		
		} catch (HBTestException e) {
			e.printStackTrace();
		}
	}
 

}
