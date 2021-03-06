package com.pointwest.java.main;

import com.pointwest.java.bean.UserSession;
import com.pointwest.java.constant.SystemConstant;
import com.pointwest.java.ui.HomePage;
import com.pointwest.java.ui.LoginPage;
import com.pointwest.java.ui.SearchEmployeePage;
import com.pointwest.java.ui.ViewSeatPlanPage;

import java.util.List;
import java.util.Map;

import com.pointwest.java.bean.Employee;

public class PeopleLocatorSystemMain {

	public static void main(String[] args) {
		List<Employee> employeeList;
		Map<String, Employee> employeeSeatMap;
		UserSession userSession = new UserSession();
		boolean isTerminated = false;

		// main loop
		do {
			
			// login loop that will end upon valid user login
			do {
				// initializes login page UI and displays the login page menu
				LoginPage loginPage = new LoginPage();
				loginPage.displayHeader(userSession);
				loginPage.displayMainContent();
				loginPage.getUserInput(userSession, SystemConstant.MENU_INPUT_HELPER_ID_LOGIN_MAIN);
				loginPage.callLoginManager(userSession);
			} while (!userSession.isLoggedIn());

			// home page loop that will end when the user decides to log out
			do {
				// initializes home page UI and displays the home page menu
				HomePage homePage = new HomePage();
				homePage.displayHeader(userSession);
				homePage.displayMainContent();
				homePage.getUserInput(userSession, SystemConstant.MENU_INPUT_HELPER_ID_HOME_MAIN);
				
				// switch case that will determine the main menu operation to be performed
				switch (userSession.getHomePageChoice()) {
				
				// case for search option
				case SystemConstant.OPTION_SEARCH_VARIABLE:
					boolean isEndSearch = false;
					
					// search loop that end when the user choose to return to home after performing a search
					do {
						
						// initializes search page UI and displays the search menu
						SearchEmployeePage searchPage = new SearchEmployeePage();
						searchPage.displayHeader(userSession);
						searchPage.displayMainContent();
						searchPage.getUserInput(userSession, SystemConstant.MENU_INPUT_HELPER_ID_SEARCH_MAIN);
						
						// displays the search by menu and calls the search manager
						if (!SystemConstant.OPTION_INVALID_VARIABLE.equalsIgnoreCase(userSession.getSearchPageSearchByChoice())) {
							searchPage.DisplaySearchByMenu(userSession);
							searchPage.getSearchValue(userSession, SystemConstant.MENU_INPUT_HELPER_ID_VALUE_INPUT);
							employeeList = searchPage.callSearchManager(userSession);
							
							if(null != employeeList) {
								searchPage.displaySearchResult(employeeList, userSession);
							}
							
							boolean isValidOption = false;
							
							// search again loop that will end when the user decides to end searching or search again
							do {
								searchPage.displayContinueSearchMenu();
								searchPage.getUserInput(userSession, SystemConstant.MENU_INPUT_HELPER_ID_OPERATION_CONTINUE);
								
								if (SystemConstant.OPTION_END_VARIABLE.equalsIgnoreCase(userSession.getSearchPageSearchByChoice())) {
									isValidOption = true;
									isEndSearch = true;
								} else if(SystemConstant.OPTION_CONTINUE_VARIABLE.equalsIgnoreCase(userSession.getSearchPageSearchByChoice())) {
									isValidOption = true;	
								}else {
									searchPage.displayInvalidChoiceMessage();
								}		
							}while(!isValidOption);
							
						} else {
							searchPage.displayInvalidChoiceMessage();
						}
					} while (!isEndSearch);
					break;
				
				// case for view option
				case SystemConstant.OPTION_VIEW_VARIABLE:
					boolean isEndView = false;
					
					// view seat plan loop that will end when the user decides to go back to home page after viewing seat plan
					do {
						ViewSeatPlanPage viewSeatPlanPage = new ViewSeatPlanPage();
						viewSeatPlanPage.displayHeader(userSession);
						viewSeatPlanPage.displayMainContent();
						viewSeatPlanPage.getUserInput(userSession, SystemConstant.MENU_INPUT_HELPER_ID_VIEW_MAIN);
						
						// will get the user input for viewing the desired seat plan
						if(!SystemConstant.OPTION_INVALID_VARIABLE.equalsIgnoreCase(userSession.getViewPageViewByChoice())) {
							viewSeatPlanPage.displayHeader(userSession);
							
							// input menu that will ask the building id
							viewSeatPlanPage.displayViewByLocation();
							viewSeatPlanPage.getLocationInput(userSession, SystemConstant.MENU_INPUT_HELPER_ID_VALUE_INPUT);
							
							// input menu that will ask the floor level
							viewSeatPlanPage.displayViewByFloorLevel();
							viewSeatPlanPage.getFloorInput(userSession, SystemConstant.MENU_INPUT_HELPER_ID_VALUE_INPUT);
							
							// additional input menu if user chose to view seat plan by quadrant
							if(SystemConstant.OPTION_VIEW_BY_QUADRANT.equalsIgnoreCase(userSession.getViewPageViewByChoice())) {
								viewSeatPlanPage.displayViewByQuadrant();
								viewSeatPlanPage.getQuadrantInput(userSession, SystemConstant.MENU_INPUT_HELPER_ID_VALUE_INPUT);
								employeeSeatMap = viewSeatPlanPage.callViewSeatPlanManager(userSession);
								if(null != employeeSeatMap) {
									viewSeatPlanPage.displayViewByQuadrantResult(employeeSeatMap, userSession);
								}
							}else {
								employeeSeatMap = viewSeatPlanPage.callViewSeatPlanManager(userSession);
								if(null != employeeSeatMap) {
									viewSeatPlanPage.displayViewByFloorResult(employeeSeatMap, userSession);
								}
							}
							boolean isValidOption = false;
							
							// view seat plan again loop that will end when user decides to end viewing or go back to home page
							do {
								viewSeatPlanPage.displayContinueSearchMenu();
								viewSeatPlanPage.getUserInput(userSession, SystemConstant.MENU_INPUT_HELPER_ID_OPERATION_CONTINUE);
								
								if (SystemConstant.OPTION_END_VARIABLE.equalsIgnoreCase(userSession.getViewPageViewByChoice())) {
									isValidOption = true;
									isEndView = true;
								} else if(SystemConstant.OPTION_CONTINUE_VARIABLE.equalsIgnoreCase(userSession.getViewPageViewByChoice())) {
									isValidOption = true;
								}else {
									viewSeatPlanPage.displayInvalidChoiceMessage();
								}
							}while(!isValidOption);	
						
						}else {
							viewSeatPlanPage.displayInvalidChoiceMessage();
						}
					}while(!isEndView);
					break;
					
				// case for logging out
				case SystemConstant.OPTION_LOGOUT_VARIABLE:
					userSession.setLoggedIn(false);
					homePage.displayLogoutMessage();
					break;
				
				// case for providing an invalid user input for the home page menu options
				case SystemConstant.OPTION_INVALID_VARIABLE:
					homePage.displayInvalidChoiceMessage();
					break;
				}

			} while (userSession.isLoggedIn());

		} while (!isTerminated);

	}

}
