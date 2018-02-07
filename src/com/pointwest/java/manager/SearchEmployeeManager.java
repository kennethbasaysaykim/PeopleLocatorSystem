package com.pointwest.java.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.pointwest.java.bean.Employee;
import com.pointwest.java.bean.UserSession;
import com.pointwest.java.dao.SearchEmployeeDao;
import com.pointwest.java.util.PlsException;

public class SearchEmployeeManager {
	Logger logger = Logger.getLogger(SearchEmployeeManager.class);
	public List<Employee> employeeList;

	public List<Employee> searchEmployee(UserSession inUserSession) throws PlsException {
		logger.info("SearchEmployeeManager > searchEmployee started");
		SearchEmployeeDao searchDao = new SearchEmployeeDao();

		switch (inUserSession.getSearchPageSearchByChoice()) {

		case "ID":
			employeeList = searchDao.retriveEmployeeByEmpId(inUserSession.getSearchPageSearchValue());
			break;
			
		case "NAME":
			employeeList = searchDao.retrieveEmployeeByName(inUserSession.getSearchPageSearchValue());
			break;
			
		case "PROJECT":
			employeeList = searchDao.retrieveEmployeeByProject(inUserSession.getSearchPageSearchValue());
			break;
		}

		logger.info("SearchEmployeeManager > searchEmployee completed");
		return employeeList;
	}

}
