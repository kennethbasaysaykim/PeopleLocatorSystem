package com.pointwest.java.constant;

public interface SqlConstant {
	public final static String FORNAME = "com.mysql.jdbc.Driver";
	public final static String DATABASE = "jdbc:mysql://localhost:3306/plsdb";
	public final static String DATABASE_USERNAME = "kenneth.kim";
	public final static String DATABASE_PASSWORD = "password123";
	
	// queries
	public final static String SQL_USER_LOGIN = 
			"SELECT * "
			+ "FROM employee WHERE binary username = ? AND binary password = ?";
	
	public final static String SQL_SEARCH_EMP_BY_ID = 
			"SELECT"
				+ " emp.emp_id, emp.first_name, emp.last_name,"
				+ " seat.bldg_id, seat.floor_number, 'F', seat.quadrant, seat.row_number, '-', seat.column_number, seat.local_number, emp.shift,GROUP_CONCAT(proj.proj_name) as 'project(s)'"
				+ " FROM employee emp"
				+ " LEFT JOIN employee_seat empSeat on emp.emp_id = empSeat.emp_id"
				+ " LEFT JOIN seat seat on empSeat.seat_id = seat.seat_id"
				+ " LEFT JOIN employee_project empProj on emp.emp_id = empProj.employee_id"
				+ " LEFT JOIN project proj on empProj.proj_alias = proj.proj_alias"
				+ " WHERE emp.emp_id LIKE ?"
				+ " GROUP BY emp.emp_id, seat.bldg_id";		
	
	public final static String SQL_SEARCH_EMP_BY_NAME = 
			"SELECT"
				+ " emp.emp_id, emp.first_name, emp.last_name,"
				+ " seat.bldg_id, seat.floor_number, 'F', seat.quadrant, seat.row_number, '-', seat.column_number, seat.local_number, emp.shift,GROUP_CONCAT(proj.proj_name) as 'project(s)'"
				+ " FROM employee emp"
				+ " LEFT JOIN employee_seat empSeat on emp.emp_id = empSeat.emp_id"
				+ " LEFT JOIN seat seat on empSeat.seat_id = seat.seat_id"
				+ " LEFT JOIN employee_project empProj on emp.emp_id = empProj.employee_id"
				+ " LEFT JOIN project proj on empProj.proj_alias = proj.proj_alias"
				+ " WHERE emp.first_name LIKE ? OR emp.last_name LIKE ?"
				+ " GROUP BY emp.emp_id, seat.bldg_id";		
	
	public final static String SQL_SEARCH_EMP_BY_PROJECT = 
			"SELECT"
				+ " emp.emp_id, emp.first_name, emp.last_name,"
				+ " seat.bldg_id, seat.floor_number, 'F', seat.quadrant, seat.row_number, '-', seat.column_number, seat.local_number, emp.shift,GROUP_CONCAT(proj.proj_name) as 'project(s)'"
				+ " FROM employee emp"
				+ " LEFT JOIN employee_seat empSeat on emp.emp_id = empSeat.emp_id"
				+ " LEFT JOIN seat seat on empSeat.seat_id = seat.seat_id"
				+ " LEFT JOIN employee_project empProj on emp.emp_id = empProj.employee_id"
				+ " LEFT JOIN project proj on empProj.proj_alias = proj.proj_alias"
				+ " WHERE proj.proj_name LIKE ? AND proj.proj_name NOT like 'Project Never Exist'"
				+ " GROUP BY emp.emp_id, seat.bldg_id";	
	
	public final static String SQL_VIEW_SEATPLAN_BY_FLOOR = 
			"SELECT"
				+ " seat.seat_id, emp.first_name, emp.last_name,"
				+ " seat.bldg_id, loc.bldg_address, seat.floor_number, seat.quadrant, seat.row_number, seat.column_number, seat.local_number"
				+ " FROM seat seat "
				+ " LEFT JOIN location loc on seat.bldg_id = loc.bldg_id "
				+ " LEFT JOIN employee_seat empSeat on seat.seat_id = empSeat.seat_id "
				+ " LEFT JOIN employee emp on empSeat.emp_id = emp.emp_id "
				+ " WHERE seat.bldg_id LIKE ? AND seat.floor_number LIKE ? "
				+ " GROUP BY seat.seat_id"
				+ " ORDER BY seat.quadrant, seat.row_number, seat.column_number";			
	
	public final static String SQL_VIEW_SEATPLAN_BY_Quadrant = 
			"SELECT"
				+ " seat.seat_id, emp.first_name, emp.last_name,"
				+ " seat.bldg_id, loc.bldg_address, seat.floor_number, seat.quadrant, seat.row_number, seat.column_number, seat.local_number"
				+ " FROM seat seat "
				+ " LEFT JOIN location loc on seat.bldg_id = loc.bldg_id "
				+ " LEFT JOIN employee_seat empSeat on seat.seat_id = empSeat.seat_id "
				+ " LEFT JOIN employee emp on empSeat.emp_id = emp.emp_id "
				+ " WHERE seat.bldg_id LIKE ? AND seat.floor_number LIKE ? AND seat.quadrant LIKE ?"
				+ " GROUP BY seat.seat_id"
				+ " ORDER BY seat.quadrant, seat.row_number, seat.column_number";			
}	
