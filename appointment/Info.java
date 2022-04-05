
import java.sql.*;
import java.util.*;

class Hospital1 {
	int v_code;
	String h_name;
	String local;
	int vaccine;
	ArrayList<Hospital1> hos = new ArrayList<Hospital1>();
	ArrayList<Object> VA = new ArrayList<>();
	ArrayList<Object> KIND = new ArrayList<>();
	ArrayList<Object> Reservation = new ArrayList<>();

	static int a = 0;
	static String Info = "";
	static int index = 0;

	Hospital1() {

	}

	Hospital1(int v_code, String h_name, String local, int vaccine) {
		this.v_code = v_code;
		this.h_name = h_name;
		this.local = local;
		this.vaccine = vaccine;
	}

	void save() {

		String url = "jdbc:mysql://localhost:3306/covid?serverTimezone=UTC";
		String user = "root";
		String pass = "1234";
		// String sql = "UPDATE Hospital Set vaccine = vaccine + 1 WHERE v_code = ?";

		Connection c = null;
		Statement s;
		ResultSet r;

		// PreparedStatement p = null;

		Scanner sc = new Scanner(System.in);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection(url, user, pass);

			s = c.createStatement();

			String q = "SELECT * FROM Hospital";
			s.execute(q);
			r = s.executeQuery(q);

			while (r.next()) {
				int index = 1;
				v_code = r.getInt(index++);
				h_name = r.getString(index++);
				local = r.getString(index++);
				vaccine = r.getInt(index++);

				hos.add(new Hospital1(v_code, h_name, local, vaccine));
			}

			r.close();
			s.close();
			c.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
//		for (Hospital1 a : hos) {
//			System.out.println("백신 코드 : " + a.v_code + " 병원 이름 : " + a.h_name + " 지역 : " + a.local + " 백신 개수 : " + a.vaccine);
//		}

		KIND.add("모더나");
		KIND.add("화이자");
		KIND.add("아스트라");
	}

	void Copy() {
		for (int i = 0; i < hos.size(); i++) {
			VA.add(hos.get(i).vaccine);
		}
		for (int i = 0; i < VA.size(); i++)
			System.out.println(VA.get(i));
	}

	void Search() { // 병원찾기
		Scanner sc = new Scanner(System.in);
		int x, y;
		do {
			System.out.println("지역을 선택해주세요.");
			System.out.println("1.서울 2.천안 3.아산");
			x = sc.nextInt();
			switch (x) {
			case 1:
				System.out.println("서울지역에 있는 병원입니다");
				for (int i = 6; i < hos.size(); i++) {
					System.out.println(hos.get(i).local + "/" + hos.get(i).h_name + "/" + KIND.get(i - 6) + " "
							+ hos.get(i).vaccine + "개");
				}
				break;
			case 2:
				System.out.println("천안지역에 있는 병원입니다");
				for (int i = 0; i < 3; i++) {
					System.out.println(hos.get(i).local + "/" + hos.get(i).h_name + "/" + KIND.get(i) + " "
							+ hos.get(i).vaccine + "개");
				}
				break;
			case 3:
				System.out.println("아산지역에 있는 병원입니다");
				for (int i = 3; i < 6; i++) {
					System.out.println(hos.get(i).local + "/" + hos.get(i).h_name + "/" + KIND.get(i - 3) + " "
							+ hos.get(i).vaccine + "개");
				}
				break;
			}
			System.out.println("1 예약하기 2 다른지역정보확인");
			a = x;
			y = sc.nextInt();
		} while (y == 2);
	}

	void Reservation() { // 예약하기
		int n1;
		Scanner sc = new Scanner(System.in);
		System.out.println("예약하실 병원을 선택해주세요");
		if (a == 1) {
			for (int i = 6; i < hos.size(); i++) {
				System.out.println((i - 5) + "." + hos.get(i).local + "/" + hos.get(i).h_name + "/" + KIND.get(i - 6)
						+ " " + hos.get(i).vaccine + "개");
			}
			n1 = sc.nextInt();
			if (n1 == 1) {
				Date D = new Date();
				System.out.println("예약이 완료되었습니다");
				System.out.println("예약정보: " + D.month + "/" + D.day + " " + hos.get(n1 + 5).local + "/"
						+ hos.get(n1 + 5).h_name + "/" + KIND.get(n1 - 1));
				Info = "예약정보: " + D.month + "/" + D.day + " " + hos.get(n1 + 5).local + "/" + hos.get(n1 + 5).h_name
						+ "/" + KIND.get(n1 - 1);
				--hos.get(n1 + 5).vaccine;
//	    		  Reservation.add(hos.get(0).v_code-1,D.month);
//	    		  Reservation.add(r,D.day);
//	    		  Reservation.add(r,hos.get(n1+5).h_name);
				index = n1 + 5;
			} else if (n1 == 2) {
				Date D = new Date();
				System.out.println("예약이 완료되었습니다");
				System.out.println("예약정보: " + D.month + "/" + D.day + " " + hos.get(n1 + 5).local + "/"
						+ hos.get(n1 + 5).h_name + "/" + KIND.get(n1 - 1));
				Info = "예약정보: " + D.month + "/" + D.day + " " + hos.get(n1 + 5).local + "/" + hos.get(n1 + 5).h_name
						+ "/" + KIND.get(n1 - 1);
//	    	      Reservation.add(r,D.month);
//	    		  Reservation.add(r,D.day);
//	    		  Reservation.add(r,hos.get(n1+5).h_name);

				--hos.get(n1 + 5).vaccine;
				index = n1 + 5;
			} else if (n1 == 3) {
				Date D = new Date();
				System.out.println("예약이 완료되었습니다");
				System.out.println("예약정보: " + D.month + "/" + D.day + " " + hos.get(n1 + 5).local + "/"
						+ hos.get(n1 + 5).h_name + "/" + KIND.get(n1 - 1));
				Info = "예약정보: " + D.month + "/" + D.day + " " + hos.get(n1 + 5).local + "/" + hos.get(n1 + 5).h_name
						+ "/" + KIND.get(n1 - 1);
//	    	      Reservation.add(2,D.month);
//	    		  Reservation.add(2,D.day);
//	    		  Reservation.add(2,hos.get(n1+5).h_name);
				--hos.get(n1 + 5).vaccine;
				index = n1 + 5;
			}

		} else if (a == 2) {
			for (int i = 0; i < 3; i++) {
				System.out.println((i + 1) + "." + hos.get(i).local + "/" + hos.get(i).h_name + "/" + KIND.get(i) + " "
						+ hos.get(i).vaccine + "개");
			}
			n1 = sc.nextInt();
			if (n1 == 1) {
				Date D = new Date();
				System.out.println("예약이 완료되었습니다");
				System.out.println("예약정보: " + D.month + "/" + D.day + " " + hos.get(n1 - 1).local + "/"
						+ hos.get(n1 - 1).h_name + "/" + KIND.get(n1 - 1));
				Info = "예약정보: " + D.month + "/" + D.day + " " + hos.get(n1 - 1).local + "/" + hos.get(n1 - 1).h_name
						+ "/" + KIND.get(n1 - 1);
				--hos.get(n1 - 1).vaccine;
				index = n1 - 1;
			} else if (n1 == 2) {
				Date D = new Date();
				System.out.println("예약이 완료되었습니다");
				System.out.println("예약정보: " + D.month + "/" + D.day + " " + hos.get(n1 - 1).local + "/"
						+ hos.get(n1 - 1).h_name + "/" + KIND.get(n1 - 1));
				Info = "예약정보: " + D.month + "/" + D.day + " " + hos.get(n1 - 1).local + "/" + hos.get(n1 - 1).h_name
						+ "/" + KIND.get(n1 - 1);
				--hos.get(n1 - 1).vaccine;
				index = n1 - 1;
			} else if (n1 == 3) {
				Date D = new Date();
				System.out.println("예약이 완료되었습니다");
				System.out.println("예약정보: " + D.month + "/" + D.day + " " + hos.get(n1 - 1).local + "/"
						+ hos.get(n1 - 1).h_name + "/" + KIND.get(n1 - 1));
				Info = "예약정보: " + D.month + "/" + D.day + " " + hos.get(n1 - 1).local + "/" + hos.get(n1 - 1).h_name
						+ "/" + KIND.get(n1 - 1);
				--hos.get(n1 - 1).vaccine;
				index = n1 - 1;
			}
		} else if (a == 3) {
			for (int i = 3; i < 6; i++) {
				System.out.println((i - 2) + "." + hos.get(i).local + "/" + hos.get(i).h_name + "/" + KIND.get(i - 3)
						+ " " + hos.get(i).vaccine + "개");
			}
			n1 = sc.nextInt();
			if (n1 == 1) {
				Date D = new Date();
				System.out.println("예약이 완료되었습니다");
				System.out.println("예약정보: " + D.month + "/" + D.day + " " + hos.get(n1 + 2).local + "/"
						+ hos.get(n1 + 2).h_name + "/" + KIND.get(n1 - 1));
				Info = "예약정보: " + D.month + "/" + D.day + " " + hos.get(n1 + 2).local + "/" + hos.get(n1 + 2).h_name
						+ "/" + KIND.get(n1 - 1);
				--hos.get(n1 + 2).vaccine;
				index = n1 + 2;
			} else if (n1 == 2) {
				Date D = new Date();
				System.out.println("예약이 완료되었습니다");
				System.out.println("예약정보: " + D.month + "/" + D.day + " " + hos.get(n1 + 2).local + "/"
						+ hos.get(n1 + 2).h_name + "/" + KIND.get(n1 - 1));
				Info = "예약정보: " + D.month + "/" + D.day + " " + hos.get(n1 + 2).local + "/" + hos.get(n1 + 2).h_name
						+ "/" + KIND.get(n1 - 1);
				--hos.get(n1 + 2).vaccine;
				index = n1 + 2;
			} else if (n1 == 3) {
				Date D = new Date();
				System.out.println("예약이 완료되었습니다");
				System.out.println("예약정보: " + D.month + "/" + D.day + " " + hos.get(n1 + 2).local + "/"
						+ hos.get(n1 + 2).h_name + "/" + KIND.get(n1 - 1));
				Info = "예약정보: " + D.month + "/" + D.day + " " + hos.get(n1 + 2).local + "/" + hos.get(n1 + 2).h_name
						+ "/" + KIND.get(n1 - 1);
				--hos.get(n1 + 2).vaccine;
				index = n1 + 2;
			}
		}

	}

	String Information() {
//		System.out.println(Info);
		return Info;
	}

	void Cancell() { // 예약취소
		int x = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("예약을 취소하시겠습니까?");
		System.out.println("1.예 2.아니요");
		x = sc.nextInt();
		if (x == 1) {
			Info = "";
			++hos.get(index).vaccine;
			System.out.println("예약이 취소되었습니다");
		} else if (x == 2) {
			System.out.println("첫 화면으로 돌아갑니다");
		}
	}

	void Seoul() {
		for (int i = 6; i < hos.size(); i++) {
			System.out.println(hos.get(i).local + "/" + hos.get(i).h_name + "/" + KIND.get(i - 6) + " "
					+ hos.get(i).vaccine + "개");
		}
	}

	void Cheonan() {
		for (int i = 0; i < 3; i++) {
			System.out.println(
					hos.get(i).local + "/" + hos.get(i).h_name + "/" + KIND.get(i) + " " + hos.get(i).vaccine + "개");
		}
	}

	void Asan() {
		for (int i = 4; i < 7; i++) {
			System.out.println(hos.get(i).local + "/" + hos.get(i).h_name + "/" + KIND.get(i - 4) + " "
					+ hos.get(i).vaccine + "개");
		}
	}

	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String USERNAME = "root";// DBMS접속 시 아이디
	private static final String PASSWORD = "1234";// DBMS접속 시 비밀번호
	private static final String URL = "jdbc:mysql://localhost:3306/" + "covid?useUnicode=true&characterEncoding=euckr";

	void add_hos() {
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

			int v_code, vaccine;
			String h_name, local;

			for (int i = 0; i < hos.size(); i++) {

				int a1 = hos.get(i).v_code;
				v_code = a1;

				String a2 = hos.get(i).h_name;
				h_name = a2;

				String a3 = hos.get(i).local;
				local = a3;

				int a4 = hos.get(i).vaccine;
				vaccine = a4;

				PreparedStatement ps = conn.prepareStatement("INSERT INTO Hospital VALUES(?,?,?,?)");

				ps.setInt(1, v_code);
				ps.setString(2, h_name);
				ps.setString(3, local);
				ps.setInt(4, vaccine);
				ps.executeUpdate();

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

class Date {
	int month = 0, day = 0;
	int x = 0;
	int y = 0;
	Scanner s = new Scanner(System.in);

	Date() {
		do {
			System.out.println("원하시는 달을 입력해주세요");
			this.month = s.nextInt();
			if (this.month == 0 || (this.month < 1 || this.month > 12)) {
				System.out.println("다시 입력해주세요");
				continue;
			}
			x = 1;
		} while (x != 1);
		do {
			System.out.println("날짜를 입력해주세요");
			this.day = s.nextInt();
			if (this.day == 0 || this.day > 31) {
				System.out.println("다시 입력해주세요");
				continue;
			}
			y = 1;
		} while (y != 1);
	}
}
