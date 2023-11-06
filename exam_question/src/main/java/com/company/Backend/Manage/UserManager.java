package com.company.Backend.Manage;

import com.company.Backend.Entity.User;
import com.company.Backend.Repository.DepartmentRepository;
import com.company.Backend.Repository.UserRepository;
import com.company.Backend.Utils.ScannerUtils;

import java.sql.SQLException;
import java.util.List;

public class UserManager {

	private UserRepository userRepository;
	private DepartmentRepository departmentRepository;

	public UserManager() {
		userRepository = new UserRepository();
	}

	public User login() throws SQLException {
		System.out.println("=================LOGIN================");

		while (true) {
			System.out.print("Please input your username: ");
			String username = ScannerUtils.inputUsername();

			System.out.print("Please input your password: ");
			String password = ScannerUtils.inputPassword();

			User user = userRepository.login(username, password);

			if (user != null) {
				return user;
			}

			System.out.print("Wrong username & password! please input again: ");
		}
	}

	public void displayInformation(User user) {
		System.out.println("ID: " + user.getId());
		System.out.println("fullname: " + user.getFullName());
		System.out.println("phone: " + user.getPhone());
		System.out.println("email: " + user.getEmail());
		System.out.println("username: " + user.getUserName());
		System.out.println("department: " + user.getDepartmentName());
		System.out.println("departmentID: " + user.getDepartment_id());
		System.out.println("project_id: " + user.getProject_id());
		System.out.println("project: " + user.getProjectName());
	}

	public void getAllUsers() throws SQLException {
		System.out.println("=================GET ALL USERS================");
		List<User> users = userRepository.getAllUsers();
		for (User user : users) {
			System.out.println(user);
		}
	}

	public void getAllUserByDepartmentId() throws SQLException {
		System.out.println("=================GET ALL USER BY DEPARTMENT ID================");
		System.out.print("Please input department id: ");
		int departmentId = ScannerUtils.inputId();
		List<User> users = userRepository.getAllUserByDepartmentId(departmentId);
		for (User user : users) {
			System.out.println(user);
		}
	}
	public void getAllUserByRole() throws SQLException {
		System.out.println("=================GET ALL USERS BY ROLE================");
		System.out.print("Please input role(Admin/Employee): ");
		String role = ScannerUtils.inputRole();
		List<User> users = userRepository.getAllUserByRole(role);
		for (User user : users) {
			System.out.println(user);
		}
	}


	public void searchUserByKeyword() throws SQLException {
		System.out.println("=================GET ALL USERS BY KEYWORD================");
		System.out.print("Please input keyword: ");
		String keywordSearch = ScannerUtils.inputKeyword();
		List<User> users = userRepository.searchUserByKeyword(keywordSearch);
		for (User user : users) {
			System.out.println(user);
		}
	}

	public void insertUser(User user) throws SQLException {
		System.out.println("Nhap vao fullname: ");
		String fullname = ScannerUtils.inputFullName();

		System.out.println("Nhap vao phone: ");
		String phone = ScannerUtils.inputPhone();

		System.out.println("Nhap vao email: ");
		String email = ScannerUtils.inputEmail();

		System.out.println("Nhap vao username: ");
		String username = ScannerUtils.inputUsername();

		System.out.println("Nhap vao password: ");
		String password = ScannerUtils.inputPassword();

		System.out.println("Nhap vao Department Id: ");
		int departmentID = ScannerUtils.inputId();

		System.out.println("Nhap vao project Id: ");
		int projectId = ScannerUtils.inputId();
		String role ="";
		while(true) {
			System.out.println("Nhap vao role: ");
			role = ScannerUtils.inputString();
			if (User.Role.EMPLOYEE.toString().equals(role) ||User.Role.ADMIN.toString().equals(role)) {
				break;
			}

		}
		userRepository.insertUser(fullname,phone,email,password,role, departmentID,projectId);
	}
}
