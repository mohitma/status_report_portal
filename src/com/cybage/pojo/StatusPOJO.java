package com.cybage.pojo;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cyb.Singleton.SingletonConnection;

public class StatusPOJO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private int Id, d_Id, user_Id;
	private String role, task, work_done_today, impediments;
	private String comments, status, plan;
	private String todaydate;
	private String previousdate;
	private String plan_unplan;

	public StatusPOJO() {
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getPlan_unplan() {
		return plan_unplan;
	}

	public void setPlan_unplan(String plan_unplan) {
		this.plan_unplan = plan_unplan;
	}

	public int getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}

	public String getTodaydate() {
		return todaydate;
	}

	public void setTodaydate(String todaydate) {
		this.todaydate = todaydate;
	}

	public String getPreviousdate() {
		return previousdate;
	}

	public void setPreviousdate(String previousdate) {
		this.previousdate = previousdate;
	}

	public int getD_Id() {
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

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}