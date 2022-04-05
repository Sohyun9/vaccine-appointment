import java.util.Scanner;

public class VeccineMain {

	static int num, num1, g, x, k;
	static Menu m = new Menu();
	static MemberList ml = new MemberList();
	static Hospital1 H = new Hospital1();

	static int Login1() {
		Scanner sc = new Scanner(System.in);
		System.out.println("----- 백신 예약 시스템 -----");
		System.out.println("0. 프로그램 종료, 1. 로그인, 2. 회원가입");
		System.out.print("메뉴를 선택해주세요 : ");
		num = sc.nextInt();

		return num;
	}

	static void Login2() {

		Scanner sc = new Scanner(System.in);
		int q = 0;
		Menu m = new Menu();

		do {
			System.out.println("0. 프로그램 종료 1. 찾기 2. 예약확인 3. 로그아웃 ");
			System.out.print("메뉴를 선택해주세요 : ");
			num1 = sc.nextInt();

			if (num1 == 0) {
				System.out.println("프로그램을 종료합니다.");
				k=0;
				break;
			}

			switch (num1) {
			case 1:
				H.Search();
				H.Reservation();
				q = 5;
				continue;
			case 2:
				if (H.Information() == "") {
					System.out.println(m.getNameL() + "님의 예약정보가 없습니다");
					System.out.println();
				} else {
					System.out.println(m.searchMember() + " " + H.Information());
					H.Cancell();
				}
				q = 4;
				continue;
			case 3:
				System.out.println("로그아웃합니다");
				q = 1;
			}
		} while (q != 1);

	}

	public static void main(String[] args) {

		DB_Connection.getConnection();
		Covid_User_Save c = new Covid_User_Save();
		c.covidarr();
		H.save();

		Menu m = new Menu();
		int x, q = 0;

		c.delete();
		do {
			x = Login1();
			if (x == 0) {
				System.out.println("프로그램을 종료합니다.");
				break;
			} else if (x == 1) {
				q = m.loginMember();
				if (q == 0) {
					x = 5;
					System.out.println("로그인에 실패하였습니다.");
					System.out.println();
					continue;
				} else if (q == 1) {
					c.delete();
					Login2();
					if(k==0) {
						break;
					}
					x = 5;
					continue;
				}
			} else if (x == 2) {
				c.delete();
				m.joinMember();
				x = 4;
			}
		} while (x != 1 && x != 2);
		
		c.delete();
		c.add();
		H.add_hos();
			
	}
}
