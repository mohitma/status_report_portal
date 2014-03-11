package com.cyb.statusPortalManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.cyb.Singleton.SingletonConnection;
import com.cybage.pojo.ProjectPOJO;
import com.cybage.pojo.UserPOJO;

public class UserManager {

	HttpSession session;

	public void regUser(UserPOJO user) {
		session = ServletActionContext.getRequest().getSession(false);
		Connection connection = SingletonConnection.getConnection();
		PreparedStatement pstmt = null;

		/*
		 * Insert into login
		 * (userId,userName,password1,firstName,lastName,userType)
		 * values(400,'Ram','ram','ram','leela','Normal');
		 */
		String sqlQuery = "Insert into login1 (userId,userName,password1,firstName,lastName,userType)"
				+ "values(?,?,?,?,?,?)";
		try {
			pstmt = connection.prepareStatement(sqlQuery);
			pstmt.setInt(1, user.getUserId());
			pstmt.setString(2, user.getUserName());
			// String encrptpwd = encryptPassword(user.getPassword());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getFirstName());
			pstmt.setString(5, user.getLastName());
			pstmt.setString(6, user.getUserType());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void createProject(ProjectPOJO project) {
		session = ServletActionContext.getRequest().getSession(false);
		Connection connection = SingletonConnection.getConnection();
		PreparedStatement pstmt = null;
		/*
		 * Insert into project (project_id, project_name,project_mgr) values
		 * ('CYB_100','cyb_RP','Vikram Chopra')
		 */
		String sqlQuery = "Insert into project (project_id, project_name,project_mgr) values (?,?,?)";
		try {
			pstmt = connection.prepareStatement(sqlQuery);
			pstmt.setString(1, project.getProjectCode());
			pstmt.setString(2, project.getProjectName());
			pstmt.setString(3, project.getProjectManager());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public List<String> listProject() {

		session = ServletActionContext.getRequest().getSession(false);
		Connection connection = SingletonConnection.getConnection();
		Statement stmt = null;
		List<String> projList = new ArrayList<String>();
		String sqlQuery = "select * from project";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			if (rs != null) {
				while (rs.next()) {
					projList.add(rs.getString(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return projList;
	}

	public List<String> listUser() {
		List<String> userList = new ArrayList<String>();
		session = ServletActionContext.getRequest().getSession(false);
		Connection connection = SingletonConnection.getConnection();
		Statement stmt = null;
		String sqlQuery = "select userId,userName from userproject";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			if (rs != null) {
				while (rs.next()) {
					userList.add(rs.getString(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userList;
	}

	/*
	 * private String encryptPassword(String password) { String salt =
	 * "LongStringForExtraSecurity@#$!%^&*(*)1234567890"; String
	 * unecryptedPassword = password; //
	 * -66297441bd1c8eb246150f79d86e1ef1fee9b0ef MessageDigest messageDigest =
	 * null; try { messageDigest = MessageDigest.getInstance("SHA");
	 * messageDigest.update((unecryptedPassword + salt).getBytes());
	 * 
	 * } catch (NoSuchAlgorithmException e) { e.printStackTrace(); } String
	 * encryptedPassword = (new BigInteger(messageDigest.digest()))
	 * .toString(16); return encryptedPassword;
	 * 
	 * }
	 */

}
