
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cyb.Singleton.SingletonConnection;
 

public class dbConnect {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection connection =   SingletonConnection.getConnection();


		if (connection != null) {
			System.out.println("You made it, take control your database now!");
			Statement stmt;
			try {
				stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("select * from login");
				if(rs != null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
				}
				}
				else
				{
					System.out.println("rs is null");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} else {
			System.out.println("Failed to make connection!");
		}
	
	}

}
