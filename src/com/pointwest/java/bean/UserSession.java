package com.pointwest.java.bean;

public class UserSession {
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String appRole;
	private String homePageChoice;
	private String searchPageSearchByChoice;
	private String searchPageSearchValue;
	private String viewPageViewByChoice;
	private String viewPageByLocationValue;
	private String viewPageByFloorValue;
	private String viewPageByQuadrantValue;
	private boolean isLoggedIn = false;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAppRole() {
		return appRole;
	}
	public void setAppRole(String appRole) {
		this.appRole = appRole;
	}
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	public String getHomePageChoice() {
		return homePageChoice;
	}
	public void setHomePageChoice(String homePageChoice) {
		this.homePageChoice = homePageChoice;
	}
	public String getSearchPageSearchByChoice() {
		return searchPageSearchByChoice;
	}
	public void setSearchPageSearchByChoice(String viewPageChoice) {
		this.searchPageSearchByChoice = viewPageChoice;
	}
	public String getSearchPageSearchValue() {
		return searchPageSearchValue;
	}
	public void setSearchPageSearchValue(String searchPageSearchValue) {
		this.searchPageSearchValue = searchPageSearchValue;
	}
	public String getViewPageViewByChoice() {
		return viewPageViewByChoice;
	}
	public void setViewPageViewByChoice(String viewPageViewByChoice) {
		this.viewPageViewByChoice = viewPageViewByChoice;
	}
	public String getViewPageByLocationValue() {
		return viewPageByLocationValue;
	}
	public void setViewPageByLocationValue(String viewPageByLocationValue) {
		this.viewPageByLocationValue = viewPageByLocationValue;
	}
	public String getViewPageByFloorValue() {
		return viewPageByFloorValue;
	}
	public void setViewPageByFloorValue(String viewPageByFloorValue) {
		this.viewPageByFloorValue = viewPageByFloorValue;
	}
	public String getViewPageByQuadrantValue() {
		return viewPageByQuadrantValue;
	}
	public void setViewPageByQuadrantValue(String viewPageByQuadrantValue) {
		this.viewPageByQuadrantValue = viewPageByQuadrantValue;
	}
	
}
