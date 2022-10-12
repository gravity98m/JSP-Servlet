package com.itwillbs.test;

public class Person {
	// 이름, 나이, 휴대폰
	private String name;
	private int age;
	private Phone1 p;
	
	public Person() {}

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

	public Phone1 getP() {
		return p;
	}

	public void setP(Phone1 p) {
		this.p = p;
	}
	
}
