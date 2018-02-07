package com.pointwest.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.pointwest.java.bean.Employee;
import com.pointwest.java.bean.Location;
import com.pointwest.java.bean.Seat;
import com.pointwest.java.constant.ExceptionConstant;
import com.pointwest.java.constant.SqlConstant;
import com.pointwest.java.util.PlsException;

public class ViewSeatPlanDao extends BaseDao{
	Logger logger = Logger.getLogger(ViewSeatPlanDao.class);
	PreparedStatement preparedStatement = null;
	Connection connection = null;
	ResultSet resultSet = null;
	Map<String, Employee> employeeSeatMap;
	
	// function that will retrieve the seats based on the given building id and floor number 
	public Map<String, Employee> retrieveSeatByFloor(String inLocation, String inFloorNumber) throws PlsException {
		logger.info("ViewSeatPlanDao > retrieveSeatByFloor started");

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(SqlConstant.SQL_VIEW_SEATPLAN_BY_FLOOR);
			preparedStatement.setString(1, inLocation);
			preparedStatement.setString(2, inFloorNumber);
			logger.info("Modified prepared statement: " + preparedStatement);
			resultSet = preparedStatement.executeQuery();
			employeeSeatMap = new HashMap<>();
			generateResultList(resultSet);

		} catch (PlsException plse) {
			throw plse;
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage());
			throw new PlsException(ExceptionConstant.EXCEPTION_SQLE, sqle); // error here
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new PlsException(ExceptionConstant.EXCEPTION_ERROR, e);
		} finally {
			closeResources(connection, preparedStatement, resultSet);
		}
		logger.info("ViewSeatPlanDao > retrieveSeatByFloor completed");
		return employeeSeatMap;
	}
	
	// function that will retrieve the seats based on the given building id, floor number and quadrant 																																			
	public Map<String, Employee> retrieveSeatByQuadrant(String inLocation, String inFloorNumber, String inQuadrant) throws PlsException {
		logger.info("ViewSeatPlanDao > retrieveSeatByQuadrant started");

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(SqlConstant.SQL_VIEW_SEATPLAN_BY_Quadrant);
			preparedStatement.setString(1, inLocation);
			preparedStatement.setString(2, inFloorNumber);
			preparedStatement.setString(3, inQuadrant);
			logger.info("Modified prepared statement: " + preparedStatement);
			resultSet = preparedStatement.executeQuery();
			employeeSeatMap = new HashMap<>();
			generateResultList(resultSet);
		
		} catch (PlsException plse) {
			throw plse;
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage());
			throw new PlsException(ExceptionConstant.EXCEPTION_SQLE, sqle); // error here
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new PlsException(ExceptionConstant.EXCEPTION_ERROR, e);
		} finally {
			closeResources(connection, preparedStatement, resultSet);
		}
		logger.info("ViewSeatPlanDao > retrieveSeatByQuadrant completed");
		return employeeSeatMap;
	}
	
	// function that will read through all the result set values and will populate the seat map object
	public void generateResultList(ResultSet inResultSet) throws PlsException {
		logger.info("ViewSeatPlanDao > generateResultList started");
		
		try {
			while (resultSet.next()) {
				
				// Objects initialization, which will be used to populate the employee seat map object
				Employee employee = new Employee();
				employee.setFirstName(resultSet.getString("first_name"));
				employee.setLastName(resultSet.getString("last_name"));
				
				if(null == resultSet.getString("first_name") && null == resultSet.getString("last_name")) {
					employee.setFullName(null);
				}else {
					employee.setFullName(employee.getFirstName() + "," + employee.getLastName());
				}		
			
				Location location = new Location();
				location.setBldgId(resultSet.getString("bldg_id"));
				location.setAddress(resultSet.getString("bldg_address"));
				
				Seat seat = new Seat();
				seat.setLocation(location);
				seat.setSeatId(resultSet.getString("seat_id"));
				seat.setFloorNumber(resultSet.getString("floor_number"));
				seat.setQuadrant(resultSet.getString("quadrant"));
				seat.setRowNumber(resultSet.getString("row_number"));
				seat.setColumnNumber(resultSet.getString("column_number"));
				seat.setLocalNumber(resultSet.getString("local_number"));
				employee.setSeat(seat);
				
				// populate the employee seat map object
				employeeSeatMap.put(seat.getRowNumber() + "-" + seat.getColumnNumber() + "-" + seat.getQuadrant(), employee);
			}
	
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage());
			throw new PlsException(ExceptionConstant.EXCEPTION_SQLE, sqle);
		}
		logger.info("ViewSeatPlanDao > generateResultList completed");
	}
}
