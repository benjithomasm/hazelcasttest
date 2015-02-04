package com.benjamin.hazelcasttest;

public class HBTestException extends Exception {

	private Exception wrappedException;
	
	public HBTestException(Exception wrappedException) {
		super();
		this.wrappedException = wrappedException;
	}

	public Exception getWrappedException() {
		return wrappedException;
	}
	
	public void setWrappedException(Exception wrappedException) {
		this.wrappedException = wrappedException;
	}

}
