package com.company.Backend.Repository;

import com.company.Backend.Entity.Department;
import com.company.Backend.Entity.Manager;
import com.company.Backend.Entity.Project;
import com.company.Backend.Entity.User;
import com.company.Backend.Utils.JDBCUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {

	private JDBCUtils jdbc;

	public ProjectRepository() {
		jdbc = new JDBCUtils();
	}

	public List<Project> getAllProject() throws SQLException {

		List<Project> projects = new ArrayList<>();

		// Step 2: create sql statement
		Statement statement = jdbc.getConnection().createStatement();
		String sql = "SELECT * \n" +
				"FROM project p\n" +
				"ORDER BY created_date DESC, id\n";

		// Step 3: execute SQL statement
		ResultSet resultSet = statement.executeQuery(sql);

		// Step 4: handling result
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int memberSize = resultSet.getInt("member_size");
			Manager manager = new Manager(new User(resultSet.getInt("user_id")));
			LocalDate createdDate = resultSet.getDate("created_date").toLocalDate();
			projects.add(new Project(id, name, memberSize, manager, createdDate));
		}

		// Step 5: close connection
		jdbc.getConnection().close();

		return projects;
	}

	public Project getDetailProjectId(int projectId) throws SQLException {

		String sql = "SELECT p.id, p.`name`,p.member_size \n"
				+ "FROM project p \n"
				+ "LEFT JOIN `user` u ON p.id = u.project_id \n"
				+ "WHERE p.id = ?";

		// Step 3: execute SQL statement
		PreparedStatement statement = jdbc.getConnection().prepareStatement(sql);

		// Step 3: execute SQL statement
		statement.setInt(1, projectId);
		ResultSet resultSet = statement.executeQuery();
		Project project = null;

		// Step 4: handling result
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int memberSize = resultSet.getInt("member_size");
			Manager manager = new Manager(new User(resultSet.getInt("user_id")));
			LocalDate createdDate = resultSet.getDate("created_date").toLocalDate();
		}

		// Step 5: close connection
		jdbc.getConnection().close();

		return project;
	}
	public boolean isProjectExistsByName(String name) throws SQLException {
		// Step 2: create sql statement
		String sql = "SELECT 1 FROM project WHERE LOWER(`name`) = ?";
		PreparedStatement statement = jdbc.getConnection().prepareStatement(sql);

		// Step 3: execute SQL statement
		statement.setString(1, name.toLowerCase());
		ResultSet resultSet = statement.executeQuery();

		// Step 4: handling result
		boolean isExits = resultSet.next();

		// Step 5: close connection
		jdbc.getConnection().close();

		return isExits;

	}

}
