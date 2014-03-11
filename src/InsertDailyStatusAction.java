/*package com.cyb.statusPortalAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.cyb.Singleton.SingletonConnection;
import com.opensymphony.xwork2.ActionSupport;

public class InsertDailyStatusAction extends ActionSupport {

	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 1L;
	private int d_Id, user_Id;
	private String role, task, work_done_today, impediments;
	private String comments, status, plan;
	private String todaydate;
	HttpSession session;

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}
	public int getD_Id() throws SQLException {
		Connection connection = SingletonConnection.getConnection();
		Statement stmt = connection.createStatement();
		String query = "SELECT d_Id FROM dailystatus ORDER BY d_Id DESC limit 1";
		ResultSet rs = stmt.executeQuery(query);
		if (rs != null) {
			while (rs.next()) {
				d_Id = rs.getInt(1);
				++d_Id;
			}
		}
		else
		{
			d_Id=1;
		}
		return d_Id;
	}

	public void setD_Id(int d_Id) {
		this.d_Id = d_Id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getWork_done_today() {
		return work_done_today;
	}

	public void setWork_done_today(String work_done_today) {
		this.work_done_today = work_done_today;
	}

	public String getImpediments() {
		return impediments;
	}

	public void setImpediments(String impediments) {
		this.impediments = impediments;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUser_Id() throws SQLException {
		String name = (String) session.getAttribute("userName");
		Connection connection = SingletonConnection.getConnection();
		Statement stmt = connection.createStatement();
		String query = "select userId from login  where userName =" + "'"
				+ name + "'";
		ResultSet rs = stmt.executeQuery(query);
		if (rs != null) {
			while (rs.next()) {
				user_Id = rs.getInt(1);
			}
		}
		return user_Id;
	}

	public String add() {
		
		 * HttpServletRequest request=ServletActionContext.getRequest();
		 * HttpSession session=request.getSession();
		 * 
		 * String s=(String)session.getAttribute("userName");
		 * System.out.println("User Name :"+s);
		 
		session = ServletActionContext.getRequest().getSession(false);
		System.out.println("user_Id :"+session.getAttribute("user_Id"));
		Connection connection = null;
		int i = 0;
		try {
			connection = SingletonConnection.getConnection();
			String sql = "Insert Into dailystatus (d_Id,role,task,work_done_today,impediments,comments,status1,plan_for_the_next_day,user_id,today_date) Values(?,?,?,?,?,?,?,?,?,?)";
			// String s = "insert into details values(?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, this.getD_Id());
			ps.setString(2, this.getRole());
			ps.setString(3, this.getTask());
			ps.setString(4, this.getWork_done_today());
			ps.setString(5, this.getImpediments());
			ps.setString(6, this.getComments());
			ps.setString(7, this.getStatus());
			ps.setString(8, this.getPlan());
			ps.setInt(9, this.getUser_Id());
			
			// convert String date  to date format
			String source=this.getTodaydate();              
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date date = null;
			try {
				date = sdf1.parse(source);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			java.sql.Date changeDate = new java.sql.Date(date.getTime()); 
			System.out.println("Date : "+this.getTodaydate());
			// ps.setDate(10, new java.sql.Date(2014, 22, 01));
			ps.setDate(10, (java.sql.Date) changeDate);
			System.out.println("Query : " + ps);
			
			 * System.out.println("Date :"+this.getCurrent_date()); String
			 * source="2014/1/22"; SimpleDateFormat format = new
			 * SimpleDateFormat("dd/MM/yyyy"); java.sql.Date d = (java.sql.Date)
			 * format.parse(source) ; //java.sql.Date x = new
			 * java.sql.Date(2014, 01, 22);
			 
			i = ps.executeUpdate();

			connection.commit();
			ps.close();
			System.out.println(i
					+ "record added successfully into the database");

			if (i != 0) {
				System.out.println("Successfully inserted");
				return SUCCESS;
				
			} else {
				return INPUT;
			}

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Problem with Insertion");
			e.printStackTrace();
			System.out.println(e.getMessage());
			
			return ERROR;
		} finally {
			try {
				connection.close();
				System.out.println("Connection is closed");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return INPUT;
			}
		}
	}

	public String getTodaydate() {
		return todaydate;
	}

	public void setTodaydate(String todaydate) {
		this.todaydate = todaydate;
	}

}
*/