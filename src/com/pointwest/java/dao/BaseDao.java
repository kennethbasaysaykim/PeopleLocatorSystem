package com.pointwest.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.pointwest.java.constant.ExceptionConstant;
import com.pointwest.java.constant.SqlConstant;
import com.pointwest.java.util.PlsException;

// dao class that handles the opening and closing the database connection
public abstract class BaseDao {
	Logger logger = Logger.getLogger(BaseDao.class);
	
	// method that sets the connection to the database
	protected Connection getConnection() throws PlsException{
		logger.info("BaeDao > getConnection started");
		Connection conn = null;
		
		try {
			Class.forName(SqlConstant.FORNAME);
			conn = DriverManager.getConnection(SqlConstant.DATABASE, SqlConstant.DATABASE_USERNAME,
					SqlConstant.DATABASE_PASSWORD);
		
		} catch (ClassNotFoundException cnfe) {
			logger.error(cnfe.getMessage());
			throw new PlsException(ExceptionConstant.EXCEPTION_CNFE , cnfe);
		
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage());
			throw new PlsException(ExceptionConstant.EXCEPTION_SQLE , sqle);
		
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new PlsException(ExceptionConstant.EXCEPTION_ERROR, e);
		}
		logger.info("BaeDao > getConnection completed");;
		
		return conn;
	}

	// method that closes the resources after using it for the database
	protected void closeResources(Connection conn, Statement statement, ResultSet rs) throws PlsException {
		logger.info("BaeDao > closeResources started");
		
		try {
			if (conn != null) {
				conn.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage());
			throw new PlsException(ExceptionConstant.EXCEPTION_SQLE, sqle);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new PlsException(ExceptionConstant.EXCEPTION_ERROR, e);
		}
		logger.info("BaeDao > closeResources completed");
	}
}
