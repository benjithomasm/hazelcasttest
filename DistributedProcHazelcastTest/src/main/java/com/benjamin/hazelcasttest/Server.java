package com.benjamin.hazelcasttest;

public class Server {

public Server(String address, int port) {
		super();
		this.address = address;
		this.port = port;
	}
private String address;
private int port;

public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public int getPort() {
	return port;
}
public void setPort(int port) {
	this.port = port;
}


}
