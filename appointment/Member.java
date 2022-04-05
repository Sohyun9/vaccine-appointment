import java.util.ArrayList;
import java.util.Scanner;

public class Member {

	String name;
	String phone;
	String password;
	String hospital;
	int v_code;

	Member(String name, String password, String phoneNumber, String hospital, int v_code) {
		this.name = name;
		this.password = password;
		this.phone = phoneNumber;
		this.hospital = hospital;
		this.v_code = v_code;
	}

	Member(String name, String phone, String password) {
		this.name = name;
		this.phone = phone;
		this.password = password;
	}

	Member(String hospital, int v_code) {
		this.hospital = hospital;
		this.v_code = v_code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public int getV_code() {
		return v_code;
	}

	public void setV_code(int v_code) {
		this.v_code = v_code;
	}

	public String toString() {

		return "이름 : " + name + ", 핸드폰 번호 : " + phone;
	}
}

class MemberList {

	static ArrayList<Member> al = new ArrayList<Member>();
	Member m1;

	MemberList() {
	}

	void addMember(Member m) {
		al.add(m);
	}

	void addVeccine(String name, String hospital, int v_code) {
		int a = 0;

		for (int i = 0; i < al.size(); i++) {
			if (name.equals(al.get(i).getName())) {
				al.get(i).setHospital(hospital);
				al.get(i).setV_code(v_code);
				a = i;
				break;
			}
		}

		al.add(new Member(hospital, v_code));
	}

	Member getMember(int index) {
		return al.get(index);
	}
}