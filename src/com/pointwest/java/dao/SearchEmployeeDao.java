package com.pointwest.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.pointwest.java.bean.Employee;
import com.pointwest.java.bean.Location;
import com.pointwest.java.bean.Project;
import com.pointwest.java.bean.Seat;
import com.pointwest.java.bean.UserSession;
import com.pointwest.java.constant.ExceptionConstant;
import com.pointwest.java.constant.SqlConstant;
import com.pointwest.java.util.PlsException;

// dao class that handles the employee related search queries
public class SearchEmployeeDao extends BaseDao {
	Logger logger = Logger.getLogger(SearchEmployeeDao.class);
	PreparedStatement preparedStatement = null;
	Connection connection = null;
	ResultSet resultSet = null;
	List<Employee> employeeList;
	
	// function that will retrieve the current user's information to determine if he/she can login and use the system
	public void retrieveSystemUser(UserSession inUserSession) throws PlsException {
		logger.info("SearchEmployeeDao > retrieveSystemUser started");

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(SqlConstant.SQL_USER_LOGIN);

			// conditions the will me be added to the prepared statement
			preparedStatement.setString(1, inUserSession.getUserName());
			preparedStatement.setString(2, inUserSession.getPassword());

			// executes the query in the preparedStatement
			resultSet = preparedStatement.executeQuery();
			// will get the query result and updates the values of the user session

			if (resultSet.next()) {
				inUserSession.setUserName(resultSet.getString("username"));
				inUserSession.setAppRole(resultSet.getString("role"));
				inUserSession.setLoggedIn(true);
				inUserSession.setFirstName(resultSet.getString("first_name"));
				inUserSession.setLastName(resultSet.getString("last_name"));
			}

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
		logger.info("SearchEmployeeDao > retrieveSystemUser completed");
	}
	// function that will retrieve the employee by searching for their id
	public List<Employee> retriveEmployeeByEmpId(String inEmployeeID) throws PlsException {
		logger.info("SearchEmployeeDao > retriveEmployeeByEmpId started");

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(SqlConstant.SQL_SEARCH_EMP_BY_ID);
			preparedStatement.setString(1, "%" + inEmployeeID + "%");
			logger.info("Modified prepared statement: " + preparedStatement);
			resultSet = preparedStatement.executeQuery();
			employeeList = new ArrayList<Employee>();
			generateResultList(resultSet);

		} catch (PlsException plse) {
			throw plse;
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage());
			throw new PlsException(ExceptionConstant.EXCEPTION_SQLE, sqle);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new PlsException(ExceptionConstant.EXCEPTION_ERROR, e);
		} finally {
			closeResources(connection, preparedStatement, resultSet);
		}

		logger.info("SearchEmployeeDao > retriveEmployeeByEmpId conpleted");
		return employeeList;
	}
	
	// function that will retrieve the employee by searching for their name
	public List<Employee> retrieveEmployeeByName(String inEmployeeName) throws PlsException {
		logger.info("SearchEmployeeDao > retrieveEmployeeByName started");

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(SqlConstant.SQL_SEARCH_EMP_BY_NAME);
			preparedStatement.setString(1, "%" + inEmployeeName + "%");
			preparedStatement.setString(2, "%" + inEmployeeName + "%");
			
			logger.info("Modified prepared statement: " + preparedStatement);
			resultSet = preparedStatement.executeQuery();
			employeeList = new ArrayList<Employee>();
			generateResultList(resultSet);

		} catch (PlsException plse) {
			throw plse;
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage());
			throw new PlsException(ExceptionConstant.EXCEPTION_SQLE, sqle);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new PlsException(ExceptionConstant.EXCEPTION_ERROR, e);
		} finally {
			closeResources(connection, preparedStatement, resultSet);
		}

		logger.info("SearchEmployeeDao > retrieveEmployeeByName conpleted");
		return employeeList;
	}
	
	// function that will retrieve the employee by searching for their project name
	public List<Employee> retrieveEmployeeByProject(String inEmployeeProject) throws PlsException {
		logger.info("SearchEmployeeDao > retrieveEmployeeByProject started");

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(SqlConstant.SQL_SEARCH_EMP_BY_PROJECT);
			preparedStatement.setString(1, "%" + inEmployeeProject + "%");
			logger.info("Modified prepared statement: " + preparedStatement);
			resultSet = preparedStatement.executeQuery();
			employeeList = new ArrayList<Employee>();
			generateResultList(resultSet);
			
		} catch (PlsException plse) {
			throw plse;
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage());
			throw new PlsException(ExceptionConstant.EXCEPTION_SQLE, sqle);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new PlsException(ExceptionConstant.EXCEPTION_ERROR, e);
		} finally {
			closeResources(connection, preparedStatement, resultSet);
		}

		logger.info("SearchEmployeeDao > retrieveEmployeeByProject conpleted");
		return employeeList;
	}
	
	// function that will read through all the result set values and will populate the employee list
	public void generateResultList(ResultSet inResultSet) throws PlsException {
		logger.info("SearchEmployeeDao > generateResultList started");
		try {
			while (inResultSet.next()) {
				
				// Objects initialization, which will be used to populate the employee list
				Employee employee = new Employee();
				employee.setEmpId(resultSet.getString("emp_id"));
				employee.setFirstName(resultSet.getString("first_name"));
				employee.setLastName(resultSet.getString("last_name"));
				employee.setShift(resultSet.getString("shift"));
			
				Location location = new Location();
				location.setBldgId(resultSet.getString("bldg_id"));

				Seat seat = new Seat();
				seat.setFloorNumber(resultSet.getString("floor_number"));
				seat.setQuadrant(resultSet.getString("quadrant"));
				seat.setRowNumber(resultSet.getString("row_number"));
				seat.setColumnNumber(resultSet.getString("column_number"));
				
				//determines if local number is empty
				System.out.println("local: " + resultSet.getString("local_number"));
				if(resultSet.getString("local_number").length() > 0){
					seat.setLocalNumber(resultSet.getString("local_number"));
				}else {
					seat.setLocalNumber("N/A");
				}
				seat.setLocation(location);
				
				// determines if the assigned project to the employee is valid. Will repace the project with a blank
				String project = "";
				if (null != resultSet.getString("project(s)")) {
					String projectList[] = resultSet.getString("project(s)").split(",");
					for (int counter = 0; counter < projectList.length; counter++) {
						if (!projectList[counter].contains("Never Exist")) {
							project += projectList[counter] + ",";
						}
					}
					// removes the last comma from the string
					if (project.length() != 0) {
						project = project.substring(0, project.length() - 1);
					}else {
						project = "NONE";
					}
				}else {
					project = "NONE";
				}
				// add the created employee object to the employee list
				employee.setProject(project);
				employee.setSeat(seat);
				employeeList.add(employee);
			}
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage());
			throw new PlsException(ExceptionConstant.EXCEPTION_SQLE, sqle);
		}
		logger.info("SearchEmployeeDao > generateResultList completed");
	}

}
