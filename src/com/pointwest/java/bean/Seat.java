package com.pointwest.java.bean;

public class Seat {
	private String seatId;
	private String floorNumber;
	private String quadrant;
	private String rowNumber;
	private String columnNumber;
	private String localNumber;
	private Location location;

	public String getConcatSeat() {
		return location.getBldgId() + floorNumber + "F" + quadrant + rowNumber + "-" + columnNumber;
	}
	
	
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getFloorNumber() {
		return floorNumber;
	}
	public void setFloorNumber(String floorNumber) {
		this.floorNumber = floorNumber;
	}
	public String getQuadrant() {
		return quadrant;
	}
	public void setQuadrant(String quadrant) {
		this.quadrant = quadrant;
	}
	public String getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(String rowNumber) {
		this.rowNumber = rowNumber;
	}
	public String getColumnNumber() {
		return columnNumber;
	}
	public void setColumnNumber(String columnNumber) {
		this.columnNumber = columnNumber;
	}
	public String getLocalNumber() {
		return localNumber;
	}
	public void setLocalNumber(String localNumber) {
		this.localNumber = localNumber;
	}
	
}
