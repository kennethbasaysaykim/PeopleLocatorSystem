package com.pointwest.java.ui;

import java.util.List;
import java.util.Scanner;

import com.pointwest.java.bean.Employee;
import com.pointwest.java.bean.Menu;
import com.pointwest.java.bean.UserSession;
import com.pointwest.java.constant.SystemConstant;
import com.pointwest.java.manager.SearchEmployeeManager;
import com.pointwest.java.util.MenuChoiceHelper;
import com.pointwest.java.util.PlsException;

public class SearchEmployeePage implements Menu {
	public List<Employee> employeeList;
	
	// method that will display the menu header for the login page
	@Override
	public void displayHeader(UserSession Session) {
		System.out.println("\n## SEARCH ##");
	}
	
	// method that will display the main menu for the login page
	@Override
	public void displayMainContent() {
		System.out.println("MENU:");
		System.out.println("[1] By Employee Id");
		System.out.println("[2] By Name");
		System.out.println("[3] By Project");
		System.out.print("Enter choice: ");	
	}
	
	// function that will get the user input for the search employee page menu
	@Override
	public void getUserInput(UserSession inUserSession, String inMenuId) {
		String userInput;
		Scanner scanner = new Scanner(System.in);
		userInput = scanner.nextLine();
		inUserSession.setSearchPageSearchByChoice(MenuChoiceHelper.getChoiceEquivalent(userInput, inMenuId));
	}
	
	// function that will get the user search value input for the search employee page
	public void getSearchValue(UserSession inUserSession, String inMenuId) {
		String userInput;
		Scanner scanner = new Scanner(System.in);
		userInput = scanner.nextLine();
		inUserSession.setSearchPageSearchValue(MenuChoiceHelper.getChoiceEquivalent(userInput, inMenuId));
	}
	
	// function that will display the search menu 
	public void DisplaySearchByMenu(UserSession inUserSession) {
		System.out.println("\n## SEARCH - By Employee " + inUserSession.getSearchPageSearchByChoice().toLowerCase() + " ##");
		System.out.print("Enter Employee " + inUserSession.getSearchPageSearchByChoice().toLowerCase() + ": ");
	}
	
	// function that will call the search manager
	public List<Employee> callSearchManager(UserSession inUserSession) {
		SearchEmployeeManager searchManager = new SearchEmployeeManager();
		try {
			employeeList = searchManager.searchEmployee(inUserSession);
		} catch (PlsException plse) {
			System.out.println(plse.getCustomErrorMessage());
		}
		return employeeList;	
	}
	
	// function that will display the search page search results
	public void displaySearchResult(List<Employee> employeeList) {
		System.out.println("\n## SEARCH RESULT - (" + employeeList.size() + ") ##");
		System.out.println("+------------------------------------------------------------------------------------------------------------------------------------------+");
		System.out.printf("| %-6s | %-12s | %-20s | %-20s | %-13s | %-10s | %-10s | %-24s |\n", "INDEX","EMPLOYEE ID","FIRSTNAME", "LASTNAME", "SEAT", "LOCAL", "SHIFT", "PROJECT(S)");
		System.out.println("|--------+--------------+----------------------+----------------------+---------------+------------+------------+--------------------------|");
		
		if(employeeList.size() == 0) {
			System.out.printf("| %-50s %1s %-61s|\n", " ", "--- No Results Found ---", " ");
		}else {
			for(int i = 0; i < employeeList.size(); i++) {
				System.out.printf("|%-7s | %-12s | %-20s | %-20s | %-13s | %-10s | %-10s | %-24s | \n",
						"[" + (i+1) + "]", employeeList.get(i).getEmpId(), employeeList.get(i).getFirstName(), employeeList.get(i).getLastName(), 
						employeeList.get(i).getSeat().getConcatSeat(),
						employeeList.get(i).getSeat().getLocalNumber(), employeeList.get(i).getShift(), employeeList.get(i).getProject());
				
			}
		}
		System.out.println("+------------------------------------------------------------------------------------------------------------------------------------------+\n");
	}
	
	// function that will display the next operation
	public void displayContinueSearchMenu() {
		System.out.println("ACTIONS: [1] Search Again [2] Home");
		System.out.print("Enter choice: ");	
	}
	
	// function that will display the invalid choice message
	public void displayInvalidChoiceMessage() {
		System.out.println(SystemConstant.MESSAGE_INVALID_OPTION);
	}
}
