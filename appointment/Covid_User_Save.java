
import java.util.*;
import java.sql.*;
import java.io.*;

class Covid_User_Save {

	ArrayList<Member> user = new ArrayList<Member>();
	MemberList m = new MemberList();
	int count = 0;

	void covidarr() {
		Connection conn = new DB_Connection().getConnection();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int index = 1;

		try {
			con = DB_Connection.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT * FROM member");
			pstmt = con.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				index = 1;
				String name = rs.getString(index++);
				String password = rs.getString(index++);
				String phoneNumber = rs.getString(index++);
				String hospital = rs.getString(index++);
				int v_code = rs.getInt(index++);

				user.add(new Member(name, password, phoneNumber, hospital, v_code));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		for (Member a : user) {
			m.addMember(user.get(count));
			count++;
		}

//		for(Covid_User model:user) {		//DB 데이터 가져와 졌는지 확인하는 기능
//			System.out.println("이름: "+ model.getName()+" |전화번호:"+model.getPhone());
//		}
	}

	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String USERNAME = "root";// DBMS접속 시 아이디
	private static final String PASSWORD = "1234";// DBMS접속 시 비밀번호
	private static final String URL = "jdbc:mysql://localhost:3306/" + "covid?useUnicode=true&characterEncoding=euckr";

	void delete() {
		Connection conn;
		Statement stmt;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			// System.out.println("MySQL 드라이버 로딩 성공");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// System.out.println("Connection 생성 성공");
			stmt = conn.createStatement();
			// System.out.println("Statement 생성 성공");
			
			
			stmt.executeUpdate("truncate member");
			stmt.executeUpdate("truncate hospital");
			//System.out.println("member 테이블을 정상적으로 삭제했습니다.");
			
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	void add() {
		Connection conn;
		Statement stmt;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		

		try {
			Class.forName(driver);
			// System.out.println("MySQL 드라이버 로딩 성공");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// System.out.println("Connection 생성 성공");
			stmt = conn.createStatement();
			// System.out.println("Statement 생성 성공");

			int v_code;
			String name, password, phone, hospital;

			
			
			for (int i = 0; i < m.al.size(); i++) {

				String e1 = m.al.get(i).name;
				name = e1;

				String e2 = m.al.get(i).password;
				password = e2;

				String e3 = m.al.get(i).phone;
				phone = e3;

				String e4 = m.al.get(i).hospital;
				hospital = e4;

				int e5 = m.al.get(i).v_code;
				v_code = e5;

				PreparedStatement ps = conn.prepareStatement("INSERT INTO member VALUES(?,?,?,?,?)");

				ps.setString(1, name);
				ps.setString(2, password);
				ps.setString(3, phone);
				ps.setString(4, hospital);
				ps.setInt(5, v_code);
				ps.executeUpdate();

			}
			
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

//		for(int i =0; i< m.al.size(); i++) {
//			stmt.setString(1, i + 1);
//			stmt.setString(2, user.get(3 * i)+0);
//			stmt.setString(3, user.get(3 * i)+1);
//			stmt.setString(4, user.get(3 * i)+2);
//			stmt.setInt(5, i+1);
//			stmt.executeUpdate();
//		}

	}
	
	
}