package com.cyb.statusPortalManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.struts2.ServletActionContext;

import com.cyb.Singleton.SingletonConnection;
import com.cyb.statusPortalAction.DailyStatusAction;
import com.cybage.pojo.StatusPOJO;


public class DailyStatusManager {

	HttpSession session;
	static Logger log = Logger.getLogger(DailyStatusAction.class.getName());
	private static String dateString;
	static HSSFWorkbook wb = null;

	public List<StatusPOJO> listRecords() {
		session = ServletActionContext.getRequest().getSession(false);
		Connection connection = SingletonConnection.getConnection();
		PreparedStatement stmt = null;
		List<StatusPOJO> statusList1 = new ArrayList<StatusPOJO>();
		ResultSet rs = null;
		Integer userID = (Integer) session.getAttribute("user_Id");
		java.sql.Date currentDate = new java.sql.Date(
				System.currentTimeMillis());

		String sqlFetching = "SELECT @rowid:=@rowid+1 as rowid,d_Id,role,task,plan_unplan,"
				+ " work_done_today,impediments,"
				+ " comments,status1,plan_for_the_next_day "
				+ " FROM dailystatus, (SELECT @rowid:=0) as init "
				+ " where today_date= '"
				+ currentDate
				+ "'"
				+ " and user_id = " + userID;
		try {
			stmt = connection.prepareStatement(sqlFetching);
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					StatusPOJO statusAdd = new StatusPOJO();
					statusAdd.setId(rs.getInt(1));
					statusAdd.setD_Id(rs.getInt(2));
					statusAdd.setRole(rs.getString(3));
					statusAdd.setTask(rs.getString(4));
					statusAdd.setPlan_unplan(rs.getString(5));
					statusAdd.setWork_done_today(rs.getString(6));
					statusAdd.setImpediments(rs.getString(7));
					statusAdd.setComments(rs.getString(8));
					statusAdd.setStatus(rs.getString(9));
					statusAdd.setPlan(rs.getString(10));
					statusList1.add(statusAdd);
					log.info("List:" + statusList1);
					System.out.println("List :" + statusList1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				System.out.println("Connection is closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return statusList1;
	}

	public StatusPOJO add(StatusPOJO statusPojo) {
		/*
		 * HttpServletRequest request=ServletActionContext.getRequest();
		 * HttpSession session=request.getSession();
		 * 
		 * String s=(String)session.getAttribute("userName");
		 * System.out.println("User Name :"+s);
		 */
		session = ServletActionContext.getRequest().getSession(false);
		// System.out.println("user_Id :" + session.getAttribute("user_Id"));
		log.info("user_Id :" + session.getAttribute("user_Id"));
		Connection connection = null;
		System.out.println("DID :" + statusPojo.getD_Id());

		int i = 0;
		try {
			connection = SingletonConnection.getConnection();
			String sql = "Insert Into dailystatus (d_Id,role,task,work_done_today,impediments,comments,status1,plan_for_the_next_day,user_id,today_date,plan_unplan) Values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setInt(1, new DailyStatusManager().getD_IdFromDB());
			ps.setString(2, statusPojo.getRole());
			ps.setString(3, statusPojo.getTask());
			ps.setString(4, statusPojo.getWork_done_today());
			ps.setString(5, statusPojo.getImpediments());
			ps.setString(6, statusPojo.getComments());
			ps.setString(7, statusPojo.getStatus());
			ps.setString(8, statusPojo.getPlan());
			int u_Id = (Integer) session.getAttribute("user_Id");
			ps.setInt(9, u_Id);
			// convert String date to date format
			String source = statusPojo.getTodaydate();
			/*
			 * SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
			 * java.util.Date date = convertStringDatetoUtil(source); try { date
			 * = sdf1.parse(source); } catch (ParseException e) {
			 * e.printStackTrace(); }
			 */
			// java.sql.Date changeDate = new java.sql.Date(date.getTime());
			java.sql.Date changeDate = new DailyStatusManager()
					.convertStringDatetoUtil(source);
			// System.out.println("Date : " + this.getTodaydate());
			log.info("Date : " + statusPojo.getTodaydate());
			ps.setDate(10, (java.sql.Date) changeDate);
			ps.setString(11, statusPojo.getPlan_unplan());
			// System.out.println("Query : " + ps);
			log.info("Query : " + ps);
			i = ps.executeUpdate();
			connection.commit();
			ps.close();
			if (i != 0) {
				log.info("Successfully inserted");
				System.out.println(" Records Inserted Successfully.");
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			// System.out.println("Problem with Insertion");
			log.error("problem with insertion");
			// return INPUT;
		} finally {
			try {
				connection.close();
				log.info("Connection closed");
				// System.out.println("Connection is closed");
			} catch (SQLException e) {
				e.printStackTrace();
				// return INPUT;
			}
		}
		return statusPojo;
	}

	private java.sql.Date convertStringDatetoUtil(String source) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date date = null;
		try {
			if (source.equals(null) || source.equals("")) {
			} else {
				date = sdf1.parse(source);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date changeDate = new java.sql.Date(date.getTime());
		dateString = sdf1.format(changeDate);
		return changeDate;

	}

	public List<StatusPOJO> listRecordsByDate(String previousdate) {

		session = ServletActionContext.getRequest().getSession(false);
		Connection connection = SingletonConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Integer userID = (Integer) session.getAttribute("user_Id");

		List<StatusPOJO> statusList1 = new ArrayList<StatusPOJO>();
		if(previousdate!=null || !previousdate.equalsIgnoreCase(null)|| !previousdate.equals("")) {
		java.sql.Date changeDate = new DailyStatusManager()
				.convertStringDatetoUtil(previousdate);
		

		// System.out.println("Date : " + this.getPreviousdate());
		log.info("view method's info: " + "Date : " + previousdate);
		String sqlFetching = " SELECT @rowid:=@rowid+1 as rowid,role,task,plan_unplan,"
				+ " work_done_today,impediments,"
				+ " comments,status1,plan_for_the_next_day "
				+ " FROM dailystatus, (SELECT @rowid:=0) as init "
				+ " where today_date= '"
				+ changeDate
				+ "'"
				+ " and user_id = "
				+ userID + " ORDER BY user_id ,today_date ";

		try {
			stmt = connection.prepareStatement(sqlFetching);
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					StatusPOJO statusAdd = new StatusPOJO();
					statusAdd.setD_Id(rs.getInt(1));
					statusAdd.setRole(rs.getString(2));
					statusAdd.setTask(rs.getString(3));
					statusAdd.setPlan_unplan(rs.getString(4));
					statusAdd.setWork_done_today(rs.getString(5));
					statusAdd.setImpediments(rs.getString(6));
					statusAdd.setComments(rs.getString(7));
					statusAdd.setStatus(rs.getString(8));
					statusAdd.setPlan(rs.getString(9));
					statusList1.add(statusAdd);
				}
			}
			System.out.println("List :" + statusList1);
			log.info("List :" + statusList1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				log.info("Connection is closed");
				System.out.println("Connection is closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		}
		return statusList1;
	}

	/*
	 * public String view() { session =
	 * ServletActionContext.getRequest().getSession(false); Connection
	 * connection = SingletonConnection.getConnection(); PreparedStatement stmt
	 * = null; ResultSet rs = null; Integer userID = (Integer)
	 * session.getAttribute("user_Id"); String source = this.getPreviousdate();
	 * 
	 * SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
	 * java.util.Date date = null; try {
	 * 
	 * else{ date = sdf1.parse(source); } } catch (ParseException e) {
	 * e.printStackTrace(); } java.sql.Date changeDate = new
	 * java.sql.Date(date.getTime());
	 * 
	 * if (source.equals(null) || source.equals("")) { return result; } else {
	 * java.sql.Date changeDate = new DailyStatusAction()
	 * .convertStringDatetoUtil(this.getPreviousdate());
	 * 
	 * // System.out.println("Date : " + this.getPreviousdate());
	 * log.info("view method's info: " + "Date : " + this.getPreviousdate()); //
	 * SELECT * FROM dailystatus d where today_date = '2014-01-16' AND //
	 * user_id =200 String sqlFetching =
	 * "SELECT * FROM dailystatus d where today_date= '" + changeDate + "'" +
	 * " and user_id = " + userID; try { stmt =
	 * connection.prepareStatement(sqlFetching); rs = stmt.executeQuery(); if
	 * (rs != null) { while (rs.next()) { StatusPOJO statusAdd = new
	 * StatusPOJO(); statusAdd.setD_Id(rs.getInt(1));
	 * statusAdd.setRole(rs.getString(2)); statusAdd.setTask(rs.getString(3));
	 * statusAdd.setWork_done_today(rs.getString(4));
	 * statusAdd.setImpediments(rs.getString(5));
	 * statusAdd.setComments(rs.getString(6));
	 * statusAdd.setStatus(rs.getString(7)); statusAdd.setPlan(rs.getString(8));
	 * statusList.add(statusAdd); } result = SUCCESS; } //
	 * System.out.println("List :" + statusList); log.info("List :" +
	 * statusList); } catch (SQLException e) { e.printStackTrace(); return
	 * result; } finally { try { connection.close();
	 * log.info("Connection is closed"); //
	 * System.out.println("Connection is closed"); } catch (SQLException e) {
	 * e.printStackTrace(); return result; } } } return result; }
	 *//*
		 * public StatusPOJO viewForUpdate() { session =
		 * ServletActionContext.getRequest().getSession(false); Connection
		 * connection = SingletonConnection.getConnection(); PreparedStatement
		 * stmt = null; ResultSet rs = null; Integer userID = (Integer)
		 * session.getAttribute("user_Id"); java.sql.Date changeDate = new
		 * java.sql.Date(System.currentTimeMillis()); String sqlFetching =
		 * "SELECT * FROM dailystatus d where today_date= '" + changeDate + "'"
		 * + " and user_id = " + userID; try { stmt =
		 * connection.prepareStatement(sqlFetching); rs = stmt.executeQuery();
		 * if (rs != null) { while (rs.next()) { StatusPOJO statusAdd = new
		 * StatusPOJO(); statusAdd.setD_Id(rs.getInt(1));
		 * statusAdd.setRole(rs.getString(2));
		 * statusAdd.setTask(rs.getString(3));
		 * statusAdd.setWork_done_today(rs.getString(4));
		 * statusAdd.setImpediments(rs.getString(5));
		 * statusAdd.setComments(rs.getString(6));
		 * statusAdd.setStatus(rs.getString(7));
		 * statusAdd.setPlan(rs.getString(8)); statusList.add(statusAdd); //
		 * System.out.println("List :" + statusList); log.info("List :" +
		 * statusList); return SUCCESS; } } } catch (SQLException e) {
		 * e.printStackTrace(); } finally { try { connection.close();
		 * log.info("Connection closed in view"); //
		 * System.out.println("Connection is closed"); } catch (SQLException e)
		 * { e.printStackTrace(); } } return SUCCESS; }
		 */
	public StatusPOJO update(StatusPOJO statusPojo) {
		session = ServletActionContext.getRequest().getSession(false);
		Connection connection = SingletonConnection.getConnection();
		Integer userID = (Integer) session.getAttribute("user_Id");
		java.sql.Date changeDate = new java.sql.Date(System.currentTimeMillis());
		int d_Id = statusPojo.getD_Id();
		String role = statusPojo.getRole();
		String task = statusPojo.getTask();
		String plan_unplan = statusPojo.getPlan_unplan();
		String work_done_today = statusPojo.getWork_done_today();
		String impediments = statusPojo.getImpediments();
		String comments = statusPojo.getComments();
		String status1 = statusPojo.getStatus();
		String plan_for_the_next_day = statusPojo.getPlan();

		String sqlFetching = "UPDATE dailystatus SET role='" + role
				+ "',task='" + task + "',work_done_today='" + work_done_today
				+ "',impediments ='" + impediments + "',comments='" + comments
				+ "',status1='" + status1 + "',plan_for_the_next_day='"
				+ plan_for_the_next_day + "',plan_unplan ='" + plan_unplan
				+ "' where d_Id = " + d_Id;
		/*
		 * + "' where today_date= '" + changeDate + "' and user_id = " + userID;
		 */

		try {
			PreparedStatement ps = connection.prepareStatement(sqlFetching);
			int pid = ps.executeUpdate(sqlFetching);
			connection.commit();
			ps.close();
			if (pid != 0) {
				// System.out.println("Successfully updates");
				log.info("Successfully updates in update method");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				// System.out.println("Connection is closed");
				log.info("Connection is closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return statusPojo;

	}

	public void deleteRecordsById(int d_Id) {
		session = ServletActionContext.getRequest().getSession(false);
		Connection connection = SingletonConnection.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sqlFetching = "DELETE  FROM dailystatus  where d_Id= " + d_Id;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(sqlFetching);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				log.info("Connection is closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List getRecordsByDate(String source) {
		ArrayList data = new ArrayList();
		session = ServletActionContext.getRequest().getSession(false);
		Connection connection = SingletonConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Integer userID = (Integer) session.getAttribute("user_Id");
		String userName = (String) session.getAttribute("userName");
		/*
		 * if (source.equals(null) || source.equals("")) { return recordsList; }
		 */
		java.sql.Date changeDate = new DailyStatusManager()
				.convertStringDatetoUtil(source);

		System.out.println("Date : " + changeDate);
		// SELECT * FROM dailystatus d where today_date = '2014-01-16' AND
		// user_id =200
		String sqlFetching = " SELECT @rowid:=@rowid+1 as rowid , role,task,work_done_today,impediments,"
				+ "comments,status1,plan_for_the_next_day,user_id "
				+ " FROM dailystatus, (SELECT @rowid:=0) as init "
				+ " where today_date= '"
				+ changeDate
				+ "'"
				+ " ORDER BY user_id ,today_date ";

		try {
			stmt = connection.prepareStatement(sqlFetching);
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					ArrayList recordsList1 = new ArrayList();
					String dno = String.valueOf(rs.getInt(1));
					recordsList1.add(dno);
					recordsList1.add(userName);
					recordsList1.add(rs.getString(9)); // userID
					recordsList1.add(rs.getString(2));
					recordsList1.add(rs.getString(3));
					recordsList1.add(rs.getString(4));
					recordsList1.add(rs.getString(5));
					recordsList1.add(rs.getString(6));
					recordsList1.add(rs.getString(7));
					recordsList1.add(rs.getString(8));
					data.add(recordsList1);
				}
			}
			System.out.println("List :" + data);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				System.out.println("Connection is closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}

	public StatusPOJO listRecordsById(Integer d_Id) {
		session = ServletActionContext.getRequest().getSession(false);
		Connection connection = SingletonConnection.getConnection();
		PreparedStatement stmt = null;
		StatusPOJO statusAdd = new StatusPOJO();
		ResultSet rs = null;
		String sqlFetching = "SELECT * FROM dailystatus d where d_Id= " + d_Id;
		try {
			stmt = connection.prepareStatement(sqlFetching);
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {

					statusAdd.setD_Id(rs.getInt(1));
					statusAdd.setRole(rs.getString(2));
					statusAdd.setTask(rs.getString(3));
					statusAdd.setWork_done_today(rs.getString(4));
					statusAdd.setImpediments(rs.getString(5));
					statusAdd.setComments(rs.getString(6));
					statusAdd.setStatus(rs.getString(7));
					statusAdd.setPlan(rs.getString(8));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				log.info("Connection is closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return statusAdd;
	}

	public int getD_IdFromDB() throws SQLException {
		Connection connection = SingletonConnection.getConnection();
		Statement stmt = connection.createStatement();
		String query = "SELECT d_Id FROM dailystatus ORDER BY d_Id DESC limit 1";
		ResultSet rs = stmt.executeQuery(query);
		int d_Id = 1;
		if (rs != null) {
			while (rs.next()) {
				d_Id = rs.getInt(1);
				++d_Id;
			}
		} else {
			d_Id = 1;
		}
		return d_Id;
	}

	public Set listAllUser() {
		session = ServletActionContext.getRequest().getSession(false);
		Connection connection = SingletonConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Set userSet = new TreeSet<Integer>();
		String sqlFetching = "SELECT userId,userName FROM login";

		try {
			stmt = connection.prepareStatement(sqlFetching);
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					userSet.add(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				System.out.println("Connection is closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userSet;
	}

	public List<List<String>> listAllRecordForReport(String source) {
		session = ServletActionContext.getRequest().getSession(false);
		Connection connection = SingletonConnection.getConnection();
		PreparedStatement stmt = null;
		List<List<String>> statusList1 = new ArrayList<List<String>>();
		ResultSet rs = null;
		// String currentDate = source;
		java.sql.Date changeDate = new DailyStatusManager()
				.convertStringDatetoUtil(source);
		String sqlFetching = " SELECT today_date,user_id ,"
				+ " role,task,plan_unplan,work_done_today,impediments,"
				+ " comments,status1,plan_for_the_next_day"
				+ " FROM dailystatus" + " where today_date= '" + changeDate
				+ "'" + " ORDER BY user_id ,today_date ";

		/*
		 * String sqlFetching =
		 * "SELECT @rowid:=@rowid+1 as rowid,user_Id, role,task,plan_unplan," +
		 * " work_done_today,impediments," +
		 * " comments,status1,plan_for_the_next_day " +
		 * " FROM dailystatus, (SELECT @rowid:=0) as init " +
		 * " where today_date= '" + currentDate + "'" + " order by user_Id";
		 */
		try {
			stmt = connection.prepareStatement(sqlFetching);
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					List<String> statusAdd = new ArrayList<String>();
					statusAdd.add(rs.getString(1));
					statusAdd.add(String.valueOf(rs.getInt(2)));
					statusAdd.add(rs.getString(3));
					statusAdd.add(rs.getString(4));
					statusAdd.add(rs.getString(5));
					statusAdd.add(rs.getString(6));
					statusAdd.add(rs.getString(7));
					statusAdd.add(rs.getString(8));
					statusAdd.add(rs.getString(9));
					statusAdd.add(rs.getString(10));
					statusList1.add(statusAdd);
					log.info("List:" + statusList1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				System.out.println("Connection is closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return statusList1;
	}

	public String writeRecordsInExcel(String source,HttpServletResponse response) {
		String result ="INPUT";
		String file = "DSR_Report.xls";
		String fileName = "D:\\DSRReport\\" + file;
		// Before Write Records Clear all rows 
		new DailyStatusManager().deleteRowFromExcel(fileName);
		List data = new DailyStatusManager().listAllRecordForReport(source);
	//	response.setContentType("application/vnd.ms-excel");
	//	response.addHeader("content-disposition","attachment; filename=" + fileName);
		if(data.size() ==0 || data ==null){
			result = "INPUT";
		}
		try {
			FileInputStream fis = new FileInputStream(fileName);
			wb = new HSSFWorkbook(fis);
			HSSFSheet sheet = wb.getSheetAt(0);
			short col = 2;
			int rowIdx = 0;
			rowIdx = 3;
			short cellIdx = 0;

			HSSFCellStyle cellStyle = getHeaderStyle();

			for (Iterator rows = data.iterator(); rows.hasNext();) {

				ArrayList row = (ArrayList) rows.next();
				HSSFRow hssfRow = sheet.createRow(rowIdx++);

				cellIdx = 0;
				for (Iterator cells = row.iterator(); cells.hasNext();) {
					HSSFCell hssfCell = hssfRow.createCell(cellIdx++);
					hssfCell.setCellValue((String) cells.next());
					hssfCell.setCellStyle(cellStyle);
				}
			}

			wb.setSheetName(0, "DSR");
		//	fis.close();			
			// Write Records into Excel
			FileOutputStream outs = new FileOutputStream(file);
			
			wb.write(outs);
			System.out.println("Write Successfully");
			outs.close();		
			
			
		   // response.setHeader("Content-disposition", "inline; filename="+ file);
			result = "SUCCESS";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	private HSSFCellStyle getHeaderStyle() {
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setAlignment(CellStyle.VERTICAL_CENTER);
		HSSFFont font1 = wb.createFont();
		font1.setFontName("calibri");
		font1.setFontHeightInPoints((short) 13);
		style.setFont(font1);
		// set border
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setAlignment(CellStyle.BORDER_THIN);
		return style;
	}
	
	private void deleteRowFromExcel(String fileName) {
		try {
			int tempValue = 3;
			//System.out.println("integer value:" + tempValue);
			FileInputStream file = new FileInputStream(new File(fileName));
			HSSFWorkbook wb = new HSSFWorkbook(file);
			HSSFSheet sheet = wb.getSheetAt(0);
			int lastRowNum = sheet.getLastRowNum();
			for(int i = 2; i<lastRowNum ;i++) {
			if (tempValue >= 0 && tempValue < lastRowNum) {
				sheet.shiftRows(tempValue + 1, lastRowNum, -1);
			}
			if (tempValue == lastRowNum) {
				HSSFRow removingRow = sheet.getRow(tempValue);
				if (removingRow != null) {
					sheet.removeRow(removingRow);
				}
			}
			
			FileOutputStream out = new FileOutputStream(fileName);
			wb.write(out);		
			}
			System.out.println("Deleted successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
