package com.benjamin.hazelcasttest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServerController {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/benjamin/hazelcasttest/context.xml");
		try {
				TcpServer tcpserver = (TcpServer)context.getBean("tcpserver");
				Thread thread = new TcpServerThreadWrapper(tcpserver);
				Thread.sleep(100);
				thread.start();
				Thread.sleep(100);
				System.out.println("Socket is listening for requests...");

				TcpClient client = (TcpClient)context.getBean("tcpclient");
				ServerList serverList = client.getServerlist();

				boolean wearerunning = true; 
				for (Server server:serverList.getServerList()) {
					wearerunning = wearerunning && client.checkHeartBeat(server.getPort());
				}

				if (wearerunning) {
					System.out.println("We are running");
				}
				
		} catch(InterruptedException ie) {
				ie.printStackTrace();
		} catch(HBTestException he) {
			System.out.println(he.getStackTrace());
		} finally {
				((ClassPathXmlApplicationContext)context).close();
		}
	}	

}
