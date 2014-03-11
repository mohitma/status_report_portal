package com.cyb.statusPortalAction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.cyb.Singleton.SingletonConnection;
import com.cyb.statusPortalManager.UserManager;
import com.cybage.pojo.ProjectPOJO;
import com.cybage.pojo.UserPOJO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @author rajashribh
 * 
 */
public class LoginAction extends ActionSupport implements SessionAware,ModelDriven<UserPOJO> {

	private static final long serialVersionUID = 1L;
	private SessionMap sessionmap;
	/*private String userName;
	private String password;*/
	UserManager userManager = new UserManager();
	UserPOJO user = new UserPOJO();
	ProjectPOJO project = new ProjectPOJO();
	List<String> userList = new ArrayList<String>();
	List<String> projList = new ArrayList<String>();
/*
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}*/

	public String execute() {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		String result = INPUT;
		try {
			connection = SingletonConnection.getConnection();
			stmt = (Statement) connection.createStatement();
			String query = "Select * from login1 where userName = '"
					+ user.getUserName() + "'" + "and password1 = '"
					+ user.getPassword() + "'";
			rs = stmt.executeQuery(query);
			if(rs==null)
			{
				System.out.println("Loging Incorrect !!!");
				addFieldError("Login", "Either userName or Password is incorrect !!!");
				result = INPUT;
			}
			if (rs != null) {
			while (rs.next()) {
				sessionmap.put("user_Id", rs.getInt("userId"));
				System.out.println("User Name : " + sessionmap.get("user_Id"));
				if (user.getUserName().equals(rs.getString("userName"))
						&& user.getPassword().equals(rs.getString("password1"))) {
					sessionmap.put("userName", user.getUserName());
					System.out.println("Login Successfully.");
					result = SUCCESS;
				}
			
			}
			}
		} catch (SQLException e) {
			System.out.println("Error" + e.getMessage());
			result = INPUT;
		} finally {
			try {
				connection.close();
				System.out.println("Connection is closed");
			} catch (SQLException e) {
				e.printStackTrace();
				result = INPUT;
			}
		}
		return result;
	}
	
	public String register(){
		
		userManager.regUser(user);
		return SUCCESS;
	}
	
	public String createP(){
		userManager.createProject(project);
		return SUCCESS;
	}
	public String listProjectUser() {
		userList = userManager.listUser();
		projList = userManager.listProject();
		return SUCCESS;
	}
	/*
	 * public void validate(){ if("".equals(this.getUserName())){
	 * addFieldError("userName", getText("userName.required")); }
	 * if("".equals(this.getPassword())){ addFieldError("password",
	 * getText("password.required")); } }
	 */

	@Override
	public void setSession(Map map) {
		sessionmap = (SessionMap) map;
	}

	public String logout() {
		Map session = ActionContext.getContext().getSession();
		session.remove("userName");
		session.remove("userId");
		sessionmap.invalidate();
		return "success";
	}

	@Override
	public UserPOJO getModel() {
		return user;
	}

}