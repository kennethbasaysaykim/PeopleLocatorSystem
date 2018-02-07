package com.pointwest.java.ui;

import java.util.Map;
import java.util.Scanner;
import com.pointwest.java.bean.Employee;
import com.pointwest.java.bean.Menu;
import com.pointwest.java.bean.UserSession;
import com.pointwest.java.constant.SystemConstant;
import com.pointwest.java.manager.viewSeatPlanManager;
import com.pointwest.java.util.MenuChoiceHelper;
import com.pointwest.java.util.PlsException;

public class ViewSeatPlanPage implements Menu {
	Map<String, Employee> employeeSeatMap;
	Scanner scanner = new Scanner(System.in);
	
	// function that will display the header for the view page menu
	@Override
	public void displayHeader(UserSession Session) {
		System.out.println("\n## VIEW SEATPLAN ##");
	}
	
	// function that will display the main menu of the view page menu
	@Override
	public void displayMainContent() {
		System.out.println("MENU:");
		System.out.println("Select how you want to view the seat plan");
		System.out.println("[1] By location - Floor Level");
		System.out.println("[2] By Quadrant");
		System.out.print("Enter choice: ");
	}
	
	// function that will get the user input for view page menu
	@Override
	public void getUserInput(UserSession userSession, String inMenuId) {
		String userInput;
		userInput = scanner.nextLine();
		System.out.println("user input in view: " + userInput + " menu ID: " + inMenuId );
		userSession.setViewPageViewByChoice(MenuChoiceHelper.getChoiceEquivalent(userInput, inMenuId));
	}
	
	// function displays the search page by header 
	public void displaySearchByHeader(UserSession userSession) {
		System.out.print("## VIEW SEATPLAN  - ");
		if (SystemConstant.OPTION_VIEW_BY_FLOOR_LEVEL.equalsIgnoreCase(userSession.getViewPageViewByChoice())) {
			System.out.print("By Location - Floor Level ##");
		} else {
			System.out.print("By Location - Floor Level - Quadrant ##");
		}
	}
	
	// function that ask for the location input
	public void displayViewByLocation() {
		System.out.print("Enter Location: ");
	}
	
	// function that will ask for the floor input
	public void displayViewByFloorLevel() {
		System.out.print("Enter Floor Level: ");
	}
	
	// function that will ask for the quadrant input
	public void displayViewByQuadrant() {
		System.out.print("Enter Quadrant: ");
	}

	// function that will get the location input
	public void getLocationInput(UserSession inUserSession, String inMenuId) {
		String userInput;
		userInput = scanner.nextLine();
		inUserSession.setViewPageByLocationValue(MenuChoiceHelper.getChoiceEquivalent(userInput, inMenuId));
	}
	
	//function that gets the floor input
	public void getFloorInput(UserSession inUserSession, String inMenuId) {
		String userInput;
		userInput = scanner.nextLine();
		inUserSession.setViewPageByFloorValue(MenuChoiceHelper.getChoiceEquivalent(userInput, inMenuId));
	}
	
	// function that gets the quadrant input
	public void getQuadrantInput(UserSession inUserSession, String inMenuId) {
		String userInput;
		userInput = scanner.nextLine();
		inUserSession.setViewPageByQuadrantValue(MenuChoiceHelper.getChoiceEquivalent(userInput, inMenuId));
	}
	
	// function that will initialize the seat plan manager
	public Map<String, Employee> callViewSeatPlanManager(UserSession inUserSession) {
		viewSeatPlanManager viewSeatPlanManager = new viewSeatPlanManager();

		try {
			employeeSeatMap = viewSeatPlanManager.viewSeatPlan(inUserSession);
		} catch (PlsException plse) {
			System.out.println(plse.getCustomErrorMessage());
		}
		return employeeSeatMap;
	}
	
	// displays the view by quadrant results
	public void displayViewByQuadrantResult(Map<String, Employee> inEmployeeSeatMap, UserSession userSession) {
		String bldgID;
		String address;
		String floorNumber;
		String quadrant;
		Employee employee;
		
		if (inEmployeeSeatMap.size() == 0) {
			System.out.println("\n--- No Results Found ---\n");
		} else {		
			bldgID = userSession.getViewPageByLocationValue();
			floorNumber = userSession.getViewPageByFloorValue();
			quadrant = userSession.getViewPageByQuadrantValue(); 
			address = inEmployeeSeatMap.get("1-1-" + userSession.getViewPageByQuadrantValue()).getSeat().getLocation().getAddress();
			
			System.out.println("\n## VIEW SEATPLAN ##\nLOCATION: " + bldgID + " [" + address +  "]" + ", " + "FLOOR: " + floorNumber + " QUADRANT: " + quadrant);
			System.out.println("+--------------------------------------------------------------------------------------------+");
			
			for(int rowCounter = 0; rowCounter < 3; rowCounter++) {
				for(int columnCounter = 0; columnCounter < 3; columnCounter++) {
					employee = inEmployeeSeatMap.get((rowCounter + 1) + "-" + (columnCounter + 1) + "-" + quadrant);
					System.out.printf("| %-28s ",employee.getSeat().getConcatSeat());
				}
				System.out.print("|\n");
				for(int columnCounter = 0; columnCounter < 3; columnCounter++) {
					employee = inEmployeeSeatMap.get((rowCounter + 1) + "-" + (columnCounter + 1) + "-" + quadrant);
					String tempName = employee.getFullName();
					if(null == tempName || tempName.isEmpty()) {
						System.out.printf("| %-28s ","UNOCCUPIED");
					}else {
						System.out.printf("| %-28s ",tempName);
					}
				}	

				System.out.print("|\n");
				for(int columnCounter = 0; columnCounter < 3; columnCounter++) {
					employee = inEmployeeSeatMap.get((rowCounter + 1) + "-" + (columnCounter + 1) + "-" + quadrant);
					String localNumber = employee.getSeat().getLocalNumber();
					if(null == localNumber || localNumber.isEmpty()) {
						System.out.printf("| %-28s ", "loc. N/A");
					}else {
						System.out.printf("| %-28s ", "loc." + localNumber);
					}
				}	
				System.out.print("|\n");
				System.out.println("+--------------------------------------------------------------------------------------------+");
			}
			System.out.println("+--------------------------------- End of Seatplan ------------------------------------------+");
		}
	}
	
	// displays the view by floor results
	public void displayViewByFloorResult(Map<String, Employee> inEmployeeSeatMap, UserSession userSession) {
		String address;
		String bldgID;
		String floorNumber;
		String currQuadrant;
		String upperQuadrantList[] = {"A", "B"};
		String lowerQuadrantList[] = {"C", "D"};
		String quadrantList[] = new String[2];
		boolean isSeparatorPrinted = false;
		int QuadrantRowCounter = 0;
		Employee employee;
		
		if (inEmployeeSeatMap.size() == 0) {
			System.out.println("\n--- No Results Found ---\n");
		} else {
			bldgID = userSession.getViewPageByLocationValue();
			floorNumber = userSession.getViewPageByFloorValue();
			address = inEmployeeSeatMap.get("1-1-A").getSeat().getLocation().getAddress();
			
			System.out.println("\n## VIEW SEATPLAN ##\nLOCATION: " + bldgID + " [" + address +  "]" + ", " + "FLOOR: " + floorNumber);
			System.out.println("+---------------------------------------- [QUADRANT A] ------------------------------------------------------------------------------- [QUADRANT B] --------------------------------------+");
				
			for(int rowCounter = 0; rowCounter < 6; rowCounter++) {
				if(rowCounter < 3) {
					quadrantList = upperQuadrantList;
					QuadrantRowCounter = rowCounter;
				}else {
					QuadrantRowCounter = rowCounter - 3;
					quadrantList = lowerQuadrantList;
					if(!isSeparatorPrinted) {
						System.out.println("+---------------------------------------- [QUADRANT C] ------------------------------------------------------------------------------- [QUADRANT D] --------------------------------------+");
						isSeparatorPrinted= true;
					}
				}
				for(int i = 0; i < 2; i++) {
					for(int quadrantColumnCounter = 0; quadrantColumnCounter < 3; quadrantColumnCounter++) {
						currQuadrant = quadrantList[i];
						employee = inEmployeeSeatMap.get((QuadrantRowCounter + 1) + "-" + (quadrantColumnCounter + 1) + "-" + currQuadrant);
						System.out.printf("| %-28s ",employee.getSeat().getConcatSeat());
					}
				}
				System.out.print("|\n");
				for(int j = 0; j < 2; j++) {
					for(int quadrantColumnCounter = 0; quadrantColumnCounter < 3; quadrantColumnCounter++) {
						currQuadrant = quadrantList[j];
						employee = inEmployeeSeatMap.get((QuadrantRowCounter + 1) + "-" + (quadrantColumnCounter + 1) + "-" + currQuadrant);
						String tempName = employee.getFullName();
						if(null == tempName || tempName.isEmpty()) {
							System.out.printf("| %-28s ","UNOCCUPIED");
						}else {
							System.out.printf("| %-28s ",tempName);
						}
					}	
				}	
				System.out.print("|\n");
				for(int j = 0; j < 2; j++) {
					for(int quadrantColumnCounter = 0; quadrantColumnCounter < 3; quadrantColumnCounter++) {
						currQuadrant = quadrantList[j];
						employee = inEmployeeSeatMap.get((QuadrantRowCounter + 1) + "-" + (quadrantColumnCounter + 1) + "-" + currQuadrant);
						String localNumber = employee.getSeat().getLocalNumber();
						if(null == localNumber || localNumber.isEmpty()) {
							System.out.printf("| %-28s ", "loc. N/A");
						}else {
							System.out.printf("| %-28s ", "loc." + localNumber);
						}
					}	
				}	
				System.out.print("|\n");
				System.out.println("+-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
			}
			System.out.println("+--------------------------------------------------------------------------------------- End of Seatplan ---------------------------------------------------------------------------------+");
		}
	}
	
	// function that will display the next operation
	public void displayContinueSearchMenu() {
		System.out.println("ACTIONS: [1] View Again [2] Home");
		System.out.print("Enter Choice: ");
	}

	// function that will display the invalid choice message
	public void displayInvalidChoiceMessage() {
		System.out.println(SystemConstant.MESSAGE_INVALID_OPTION);
	}
}
