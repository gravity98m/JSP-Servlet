package com.itwillbs.member;

import java.sql.Timestamp;

public class MemberBean {
	
	// itwill_member 테이블의 정보를 저장하는 객체
	private String id;
	private String pw;
	private String name;
	private int age;
	private String gender;
	private String email;
	private Timestamp regdate;
	
	public MemberBean() {
		// 생성자. 초기화메서드 -> 인스턴스 내에 어떤 값이 들어있는지 모르므로 멤버변수를 초기화시킴
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	// alt shift s + s -> 이 객체들 안에 무엇이 들었는지 확인하는 메서드. 
	// 이 메서드를 써주지 않으면, 클래스명@주소값 으로 출력됨 -> 여기에 있다는 것을 나타냄.
	@Override
	public String toString() {
		return "MemberBean [id=" + id + ", pw=" + pw + ", name=" + name + ", age=" + age + ", gender=" + gender
				+ ", email=" + email + ", regdate=" + regdate + "]";
	}
	
	
	
		
}
