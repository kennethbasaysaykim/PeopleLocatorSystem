package com.pointwest.java.util;

public class MenuChoiceHelper {
	
	// function that will convert the user's menu input to string equivalent values
	public static String getChoiceEquivalent(String inUserInput, String inMenuId) {
		String choiceEquivalent = "#";

		inUserInput = inUserInput.trim();
		inUserInput = inUserInput.toUpperCase();

		// case for selecting options within the home page
		switch (inMenuId) {
		case "HOME_MAIN":
			switch (inUserInput) {
			case "1":
				choiceEquivalent = "SEARCH";
				break;

			case "2":
				choiceEquivalent = "VIEW";
				break;

			case "3":
				choiceEquivalent = "LOGOUT";
				break;

			default:
				choiceEquivalent = "INVALID";
				break;
			}
			break;

		// case for selecting options within the view page
		case "SEARCH_MAIN":
			switch (inUserInput) {
			case "1":
				choiceEquivalent = "ID";
				break;

			case "2":
				choiceEquivalent = "NAME";
				break;

			case "3":
				choiceEquivalent = "PROJECT";
				break;

			default:
				choiceEquivalent = "INVALID";
				break;
			}
			break;

		case "OPERATION_CONTINUE":
			switch (inUserInput) {
			case "1":
				choiceEquivalent = "CONTINUE";
				break;

			case "2":
				choiceEquivalent = "END";
				break;

			default:
				choiceEquivalent = "INVALID";
				break;
			}
			break;
			
		case "VALUE_INPUT":
			if (inUserInput.contains("%")) {
				choiceEquivalent = "#";
			} else if (inUserInput.isEmpty() || choiceEquivalent == "") {
				choiceEquivalent = "#";
			} else {
				choiceEquivalent = inUserInput;
			}
			break;
			
		case "VIEW_MAIN":
			switch (inUserInput) {
		
			case "1":
				choiceEquivalent = "FLOOR";
				break;

			case "2":
				choiceEquivalent = "QUADRANT";
				break;
			
			default:
				choiceEquivalent = "INVALID";
				break;
			}
		}
		return choiceEquivalent;
	}

}
