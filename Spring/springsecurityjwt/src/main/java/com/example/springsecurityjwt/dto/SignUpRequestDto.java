package com.example.springsecurityjwt.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
	private String memberName;
	private String password;
	private String role;
	private String name;
	private String email;
	private String age;
	private String career;
	private String salary;
	private String morningTask;
	private String afternoonTask;
	private String wage;
	private String day;
	private String incentive;



	public SignUpRequestDto(String memberName, String password, String role, String name, String email, String age, String career, String salary, String morningTask, String afternoonTask, String wage, String day, String incentive) {
		super();
		this.memberName = memberName;
		this.password = password;
		this.role = role;
		this.name = name;
		this.email = email;
		this.age = age;
		this.career = career;
		this.salary = salary;
		this.morningTask = morningTask;
		this.afternoonTask = afternoonTask;
		this.wage = wage;
		this.day = day;
		this.incentive = incentive;
	}


	public String getMemberName() {
		return memberName;
	}


	public String getPassword() {
		return password;
	}


	public String getRole() {
		return role;
	}


	public String getName() {
		return name;
	}


	public String getEmail() {
		return email;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public String getAge() {
		return age;
	}

	public String getCareer() {
		return career;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getMorningTask() {
		return morningTask;
	}

	public String getAfternoonTask() {
		return afternoonTask;
	}

	public void setMorningTask(String morningTask) {
		this.morningTask = morningTask;
	}

	public void setAfternoonTask(String afternoonTask) {
		this.afternoonTask = afternoonTask;
	}

	public String getWage() {
		return wage;
	}

	public void setWage(String wage) {
		this.wage = wage;
	}

	public String getDay() {
		return day;
	}

	public String getIncentive() {
		return incentive;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public void setIncentive(String incentive) {
		this.incentive = incentive;
	}
}
