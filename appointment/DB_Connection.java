
import java.util.*;
import java.sql.*;

public class DB_Connection { // 접속
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String USERNAME = "root";// DBMS접속 시 아이디
	private static final String PASSWORD = "1234";// DBMS접속 시 비밀번호
	private static final String URL = "jdbc:mysql://localhost:3306/" + "covid?useUnicode=true&characterEncoding=euckr";// DBMS접속할
																														// db명

	public static Connection getConnection() { // 접속부
		Connection con = null; // connection 객체
		Statement stmt;
		ResultSet rs;
		try {
			Class.forName(driver);
			// System.out.println("MySQL 드라이버 로딩 성공");
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// System.out.println("Connection 생성 성공");
			stmt = con.createStatement();
			// System.out.println("Statement 생성 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}