package com.pointwest.java.ui;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.pointwest.java.bean.Menu;
import com.pointwest.java.bean.UserSession;
import com.pointwest.java.constant.ExceptionConstant;
import com.pointwest.java.constant.SqlConstant;
import com.pointwest.java.dao.SearchEmployeeDao;
import com.pointwest.java.manager.UserManager;
import com.pointwest.java.util.PlsException;

public class LoginPage implements Menu {
	Logger logger = Logger.getLogger(LoginPage.class);
			
	// method that will display the menu header for the login page
	@Override
	public void displayHeader(UserSession userSession) {
		System.out.println("\nPEOPLE LOCATOR SYSTEM");
	}
	
	@Override
	// method that will display the main menu for the login page
	public void displayMainContent() {
		System.out.println("------------------------");
		System.out.println("LOGIN");
		System.out.println("------------------------");
	}

	// function that will ask the user for a user name and password input
	@Override
	public void getUserInput(UserSession userSession, String menuID) {
		Scanner scanner = new Scanner(System.in);
		MessageDigest md5Hasher = null;
		
		try {
			md5Hasher = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
			System.out.println(ExceptionConstant.EXCEPTION_ERROR);
		}
		String userName;
		String password;
		System.out.print("Username: ");
		userName = scanner.nextLine();
		userSession.setUserName(userName);
		System.out.print("Password: ");
		password = scanner.nextLine();
		md5Hasher.update(password.getBytes(), 0,password.length());
		userSession.setPassword(new BigInteger(1,md5Hasher.digest()).toString(16));
	}
	
	// function that will initialize the login manager
	public void callLoginManager(UserSession userSession) {
		String loginStatus;
		UserManager userManager = new UserManager();
		try {
			loginStatus = userManager.loginUser(userSession);
			System.out.println(loginStatus);
		} catch (PlsException e) {
			System.out.println(e.getCustomErrorMessage());
		}
	}
	
}
