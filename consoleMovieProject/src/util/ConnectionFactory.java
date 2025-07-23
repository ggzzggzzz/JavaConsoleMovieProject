package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	public Connection getConnection()  {
	
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 슬래시(/) 사용



			String user 	= "hr";
			String password = "hr";
			
						 conn = DriverManager.getConnection(url, user, password);
			 if (conn == null) {
			        System.out.println("❌ DB 연결 실패: conn is null");
			    }
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
