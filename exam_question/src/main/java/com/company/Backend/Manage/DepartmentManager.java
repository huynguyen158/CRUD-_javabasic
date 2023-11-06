package com.company.Backend.Manage;

import java.sql.SQLException;
import java.util.List;

import com.company.Backend.Entity.Department;
import com.company.Backend.Entity.User;
import com.company.Backend.Repository.DepartmentRepository;

public class DepartmentManager {

	private DepartmentRepository departmentRepository;

	public DepartmentManager() {
		departmentRepository = new DepartmentRepository();
	}

	public void getAllDepartments() throws SQLException {
		System.out.println("=================GET ALL DEPARTMENT================");
		List<Department> departments = departmentRepository.getAllDepartments();
		for (Department department : departments) {
			System.out.println(department);
		}
	}
	public void printDepartmentDetails(User user) throws SQLException {
		System.out.println("Your current department :");
		System.out.println("department: " + user.getDepartmentName());
		System.out.println("departmentID: " + user.getDepartment_id());

	}


}
