package com.pointwest.java.ui;

import java.util.Date;
import java.util.Scanner;

import com.pointwest.java.bean.Menu;
import com.pointwest.java.bean.UserSession;
import com.pointwest.java.constant.SystemConstant;
import com.pointwest.java.util.MenuChoiceHelper;

public class HomePage implements Menu{
	
	// method that will display the menu header for the home page
	@Override
	public void displayHeader(UserSession userSession) {
		System.out.println("\n## HOME ##");
		System.out.println("-------------------------------------");
		System.out.println("Welcome " + userSession.getFirstName() + " " + userSession.getLastName() + " [" + userSession.getAppRole() + "]!");
		System.out.println("-------------------------------------");
	}
//  method that will display the menu header for the home page
	@Override
	public void displayMainContent() {
		System.out.println("MAIN MENU:");
		System.out.println("[1] Search");
		System.out.println("[2] View Seatplan");
		System.out.println("[3] Logout");
		System.out.print("Enter choice: ");	
	}
	// function that will ask the user for an input
	@Override
	public void getUserInput(UserSession userSession, String menuPart) {
		String userInput;
		Scanner scanner = new Scanner(System.in);
		userInput = scanner.nextLine();
		userSession.setHomePageChoice(MenuChoiceHelper.getChoiceEquivalent(userInput, menuPart)) ;
	}
	
	public void displayLogoutMessage() {
		System.out.println(SystemConstant.MESSAGE_LOGOUT);
	}
	
	public void displayInvalidChoiceMessage() {
		System.out.println(SystemConstant.MESSAGE_INVALID_OPTION);
	}
	
}
