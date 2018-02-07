package com.pointwest.java.manager;

import org.apache.log4j.Logger;

import com.pointwest.java.bean.UserSession;
import com.pointwest.java.dao.SearchEmployeeDao;
import com.pointwest.java.util.PlsException;

//manager class that handles login functionalities and connects the login ui to the dao
public class UserManager {
	Logger logger = Logger.getLogger(UserManager.class);
	
	public String loginUser(UserSession userSession) throws PlsException   {
		logger.info("UserManager > loginUser started");
		
		String loginStatus;
		SearchEmployeeDao employeeDao = new SearchEmployeeDao();
		employeeDao.retrieveSystemUser(userSession);
		
		if(userSession.isLoggedIn()) {
			loginStatus = "\n>> Login Successful";
			logger.info("Login Successful. Username input: " + userSession.getUserName());
		}else {
			loginStatus = "\n>> Login Failed. Username or password might be invalid";
			logger.info("Login Failed. Username input: " + userSession.getUserName());
		}
		logger.info("UserManager > loginUser completed");
		
		return loginStatus;
	}
}
