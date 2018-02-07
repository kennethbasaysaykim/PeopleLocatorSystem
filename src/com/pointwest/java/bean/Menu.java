package com.pointwest.java.bean;

public interface Menu {
	
	public abstract void displayHeader(UserSession userSession);
	
	public abstract void displayMainContent();
	
	public abstract void getUserInput(UserSession userSession, String menuId);
	
}
