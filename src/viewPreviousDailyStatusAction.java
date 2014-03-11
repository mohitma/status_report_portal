/*package com.cyb.statusPortalAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.cyb.Singleton.SingletonConnection;
import com.cybage.pojo.StatusPOJO;
import com.opensymphony.xwork2.ActionSupport;

//com.cyb.statusPortalAction.viewPreviousDailyStatusAction

public class viewPreviousDailyStatusAction extends ActionSupport{

	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 1L;
	private String previousdate;
	ArrayList<StatusPOJO> statusList = new ArrayList<StatusPOJO>();

	HttpSession session = null;

	public String getPreviousdate() {
		return previousdate;
	}

	public void setPreviousdate(String previousdate) {
		this.previousdate = previousdate;
	}

	
	public ArrayList<StatusPOJO> getList() {
		return statusList;
	}

	public void setList(ArrayList<StatusPOJO> list) {
		this.statusList = list;
	}

	public String execute() {
		return SUCCESS;
	}

	public String view() {
		session = ServletActionContext.getRequest().getSession(false);
		Connection connection = SingletonConnection.getConnection();
		PreparedStatement  stmt = null;
		ResultSet rs = null;
		Integer userID = (Integer) session.getAttribute("user_Id");
		String source = this.getPreviousdate();
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date date = null;
		try {
			date = sdf1.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date changeDate = new java.sql.Date(date.getTime());
		System.out.println("Date : " + this.getPreviousdate());
		// SELECT * FROM dailystatus d where today_date = '2014-01-16' AND
		// user_id =200
		String sqlFetching = "SELECT * FROM dailystatus d where today_date= '"
				+ changeDate + "'" + " and user_id = " + userID;
		try {
			stmt = connection.prepareStatement(sqlFetching);
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					StatusPOJO statusAdd = new StatusPOJO();
					// statusAdd.setTodaydate(rs.getDate(10));
					statusAdd.setRole(rs.getString(2));
					statusAdd.setTask(rs.getString(3));
					statusAdd.setWork_done_today(rs.getString(4));
					statusAdd.setImpediments(rs.getString(5));
					statusAdd.setComments(rs.getString(6));
					statusAdd.setStatus(rs.getString(7));
					statusAdd.setPlan(rs.getString(8));
					statusList.add(statusAdd);
					System.out.println("List :"+statusList);
				}
			}
			System.out.println("List :"+statusList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				connection.close();
				System.out.println("Connection is closed");
				return SUCCESS;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
		

	}


}*/