
import java.util.*;
import java.util.regex.Pattern;
import java.sql.*;

public class Menu {

	int cnt = 0;
	static int count = 0;
	static String nameL = "";
	static int index = 0;

	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String USERNAME = "root";// DBMS접속 시 아이디
	private static final String PASSWORD = "1234";// DBMS접속 시 비밀번호
	private static final String URL = "jdbc:mysql://localhost:3306/" + "covid?useUnicode=true&characterEncoding=euckr";// DBMS접속할

	void joinMember() { // 회원가입(완성)
		Scanner sc = new Scanner(System.in);
		Connection conn;
		Statement stmt;
		ResultSet rs;
		int v_code;
		String name, password, phone,hospital, q;

		try {
			Class.forName(driver);
			// System.out.println("MySQL 드라이버 로딩 성공");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// System.out.println("Connection 생성 성공");
			stmt = conn.createStatement();
			// System.out.println("Statement 생성 성공");

			System.out.println("회원 정보를 입력해주세요.");
			while (true) {
				do {
				System.out.print("이름: ");
				name = sc.next();
				if(!(Pattern.matches("^[ㄱ-ㅎ가-힣]*$",name))) {
					System.out.println("이름은 한글로 입력해주세요");
				}
				}while(!(Pattern.matches("^[ㄱ-ㅎ가-힣]*$",name)));
				int cnt = 0;
				do {
				System.out.print("패스워드: ");
				password = sc.next();
				for(int i = 0;i<password.length();i++) {
					char ch = password.charAt(i);
					if(ch>='0' && ch<='9')
						cnt ++;
					}
				if(cnt==0) {
					System.out.println("비밀번호는 숫자만 입력해주세요");;
					}
				}while(cnt==0);
				
				do {
				System.out.print("전화번호: ");
				phone = sc.next();
				if(!(Pattern.matches("(010|011|016|017|018?019)-(\\d{3,4})-(\\d{4})", phone))) {
					System.out.println("전화번호 입력 형식은 [XXX-XXXX-XXXX]입니다");
				}
				}while(!(Pattern.matches("(010|011|016|017|018?019)-(\\d{3,4})-(\\d{4})", phone)));
				hospital = null;
				v_code = 0;

				PreparedStatement ps = conn.prepareStatement("INSERT INTO member VALUES(?,?,?,?,?)");

				ps.setString(1, name);
				ps.setString(2, password);
				ps.setString(3, phone);
				ps.setString(4, hospital);
				ps.setInt(5, v_code);
				ps.executeUpdate();

				stmt = (Statement) conn.createStatement();
				q = " where name = '" + name + "'";
				rs = stmt.executeQuery("SELECT * FROM member" + q);

				System.out.println();
				System.out.println("-------------입력하신 정보입니다--------------");
				while (rs.next()) {
					System.out.println(" 이름>>" + rs.getString("name"));
					System.out.println(" 비밀번호>>" + rs.getString("password"));
					System.out.println(" 전화번호>>" + rs.getString("phoneNumber"));
					System.out.println();
				}

				break;
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	int loginMember() { // 로그인
		Scanner sc = new Scanner(System.in);
		Covid_User_Save c = new Covid_User_Save();
		c.covidarr();
		MemberList m = new MemberList();
		int result;
		int a = 0;

		System.out.print("이름을 입력해주세요 : ");
		nameL = sc.nextLine();
		System.out.print("비밀번호를 입력해주세요 : ");
		String password = sc.nextLine();

		for (int i = 0; i < m.al.size(); i++) {
			if (nameL.equals(m.al.get(i).getName())) {
				if (password.equals(m.al.get(i).getPassword())) {
					System.out.println("로그인을 성공하셨습니다.");
					System.out.println("로그인한 계정 : " + nameL);
					System.out.println();
					index = i;
					return 1;
				}
			}
		}
		return 0;
	}

	Member searchMember() { // 예약확인
		int index = 0;
		MemberList m = new MemberList();
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < m.al.size(); i++) {
			if (nameL.equals(m.al.get(i).getName())) {
				index = i;
			}
		}

		return m.getMember(index);
	}

	static String getNameL() {
		return nameL;
	}
}