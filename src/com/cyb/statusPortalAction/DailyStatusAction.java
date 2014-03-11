package com.cyb.statusPortalAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.cyb.statusPortalManager.DailyStatusManager;
import com.cybage.pojo.StatusPOJO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DailyStatusAction extends ActionSupport implements
		ModelDriven<StatusPOJO>, SessionAware {

	/**
	 * 
	 */
	private StatusPOJO statusPojo = new StatusPOJO();
	private List<StatusPOJO> statusList = new ArrayList<StatusPOJO>();
	static Logger log = Logger.getLogger(DailyStatusAction.class.getName());
	private DailyStatusManager dailyStatusManager;
	private static final long serialVersionUID = 1L;	
	private Map sessionMap;
	private String previousdate;
	private static int dId_ref = 0;
	HttpSession session;

	@Override
	public StatusPOJO getModel() {
		return statusPojo;
	}

	public String execute() {		
		System.out.println("In Execute Method");
		this.statusList = dailyStatusManager.listRecords();
		return SUCCESS;
	}

	public String add() {
		dId_ref = statusPojo.getD_Id();
		if (dId_ref == 0) {
			dailyStatusManager.add(getStatusPojo());
		} else {
			dailyStatusManager.update(getStatusPojo());
		}
		return SUCCESS;
	}

	public String update() {
		dailyStatusManager.update(getStatusPojo());
		return SUCCESS;
	}

	public String list() {
		statusList = dailyStatusManager.listRecords();
		return SUCCESS;
	}

	public String view() {
		if (statusPojo.getPreviousdate() != null
				|| statusPojo.getPreviousdate() != "") {

			statusList = dailyStatusManager.listRecordsByDate(statusPojo
					.getPreviousdate());
		}
		return SUCCESS;

	}

	public String editStatusById() {
		statusPojo = dailyStatusManager.listRecordsById(statusPojo.getD_Id());
	
	/*	HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		dailyStatusManager.writeRecordsInExcel(statusPojo.getPreviousdate(),
				response);
	*/	// String fileName = "DSR_Report.xls";

		/*
		 * response.setContentType("application/vnd.ms-excel");
		 * response.setHeader("Content-disposition", "inline; filename="+
		 * fileName);
		 */

		/*
		 * try { PrintWriter writer = response.getWriter();
		 * response.flushBuffer(); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */
		// response.flushBuffer();
		return SUCCESS;
	}

	@Override
	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}

	public Map getSession() {
		return sessionMap;
	}

	public String getPreviousdate() {
		return previousdate;
	}

	public void setPreviousdate(String previousdate) {
		this.previousdate = previousdate;
	}

	public DailyStatusAction() {
		dailyStatusManager = new DailyStatusManager();
	}

	public StatusPOJO getStatusPojo() {
		return statusPojo;
	}

	public void setStatusPojo(StatusPOJO statusPojo) {
		this.statusPojo = statusPojo;
	}

	public List<StatusPOJO> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<StatusPOJO> statusList) {
		this.statusList = statusList;
	}
}
