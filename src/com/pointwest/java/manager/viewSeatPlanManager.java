package com.pointwest.java.manager;
import java.util.Map;
import org.apache.log4j.Logger;
import com.pointwest.java.bean.Employee;
import com.pointwest.java.bean.UserSession;
import com.pointwest.java.dao.ViewSeatPlanDao;
import com.pointwest.java.util.PlsException;

//manager class that handles the view seats functionalities and connects the view seat ui to the dao
public class viewSeatPlanManager {
	Logger logger = Logger.getLogger(viewSeatPlanManager.class);
	Map<String, Employee> employeeSeatMap;

	public Map<String, Employee> viewSeatPlan(UserSession inUserSession) throws PlsException{
		logger.info("viewSeatPlanManager > viewSeatPlan started");
		ViewSeatPlanDao viewSeatPlanDao = new ViewSeatPlanDao();
		
		switch(inUserSession.getViewPageViewByChoice()){
			case "FLOOR":
				employeeSeatMap = viewSeatPlanDao.retrieveSeatByFloor(inUserSession.getViewPageByLocationValue(), inUserSession.getViewPageByFloorValue());
				break;
				
			case "QUADRANT":
				employeeSeatMap = viewSeatPlanDao.retrieveSeatByQuadrant(inUserSession.getViewPageByLocationValue(), inUserSession.getViewPageByFloorValue(), inUserSession.getViewPageByQuadrantValue());
				break;
		}
		logger.info("viewSeatPlanManager > viewSeatPlan compelted");
		
		return employeeSeatMap;
	}

}
