package com.pointwest.java.util;


public class PlsException extends Exception{
	private String customErrorMessage;
	private Exception e;
	
	// function that will add a custom message
	public PlsException(String customErrorMessage, Exception e) {
		super(customErrorMessage, e); 
		this.customErrorMessage = "\n" + customErrorMessage + "\n";
	}
	
	public String getCustomErrorMessage() {
		return customErrorMessage;
	}
	
}
