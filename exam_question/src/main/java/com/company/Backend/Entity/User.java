package com.company.Backend.Entity;

import java.time.LocalDate;

public class User {

	private int id;
	private String fullname;
	private String phone;
	private String email;
	private String username;
	private String password;
	private Department department;
	private Project project;
	private Role role;
	private LocalDate createdDate;

	public User(int id, String fullname, String phone, String email, String username, Department department, Project project,Role role, LocalDate createdDate) {
		this.id = id;
		this.fullname = fullname;
		this.phone = phone;
		this.email = email;
		this.username = username;
		this.department = department;
		this.project = project;
		this.role = role;
		this.createdDate = createdDate;
	}
	public User(int id, String fullname, String phone, String email, String username, Department department, Project project, LocalDate createdDate) {
		this.id = id;
		this.fullname = fullname;
		this.phone = phone;
		this.email = email;
		this.username = username;
		this.department = department;
		this.project = project;
		this.createdDate = createdDate;
	}
	public User(String fullname, String phone, String email, String username,String password, Department department, Project project, LocalDate createdDate) {
		this.fullname = fullname;
		this.phone = phone;
		this.email = email;
		this.username = username;
		this.department = department;
		this.project = project;
		this.createdDate = createdDate;
	}

	public User(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getFullName() {
		return fullname;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getPassWord() {
		return password;
	}

	public String getUserName() {
		return username;
	}

	public Department getDepartment() {
		return department;
	}

	public Project getProject() {
		return project;
	}
	public String getDepartmentName() {
		return department.getName();
	}

	public int getProject_id() {
		return project.getId();
	}
	public int getDepartment_id() {
		return department.getId();
	}
	public String getProjectName() {
		return project.getName();
	}

	public Role getRole() {
		return role;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", fullname='" + fullname + '\'' +
				", phone='" + phone + '\'' +
				", email='" + email + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", department=" + department +
				", project=" + project +
				", role=" + role +
				", createdDate=" + createdDate +
				'}';
	}

	public enum Role {
		ADMIN, EMPLOYEE
	}

}
