package com.company.Backend.Repository;

import com.company.Backend.Entity.Department;
import com.company.Backend.Entity.Project;
import com.company.Backend.Entity.User;
import com.company.Backend.Utils.DateUtils;
import com.company.Backend.Utils.JDBCUtils;

import javax.jws.soap.SOAPBinding;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private JDBCUtils jdbc;

    public UserRepository() {
        jdbc = new JDBCUtils();
    }

    public List<User> getAllUsers() throws SQLException {

        List<User> users = new ArrayList<>();

        // Step 2: create sql statement
        Statement statement = jdbc.getConnection().createStatement();
        String sql =    "SELECT u.id, u.username, u.fullname, u.phone, u.email, p.`name` as project_name, u.created_date, d.`name` as department_name \n" +
                "FROM		`user` u \n" +
                "LEFT JOIN	department d ON d.id = u.department_id \n" +
                "LEFT JOIN project p ON p.id = u.project_id \n" +
                "ORDER BY u.created_date DESC";

        // Step 3: execute SQL statement
        ResultSet resultSet = statement.executeQuery(sql);

        // Step 4: handling result
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String fullname = resultSet.getString("fullname");
            String phone = resultSet.getString("phone");
            String email = resultSet.getString("email");
            Department department = new Department(resultSet.getString("department_name"));
            Project project = new Project(resultSet.getString("project_name"));
            LocalDate createdDate = resultSet.getDate("created_date").toLocalDate();

            users.add(new User(id, username, fullname, phone, email ,department,project, createdDate));
        }

        // Step 5: close connection
        jdbc.getConnection().close();

        return users;
    }

    public List<User> getAllUserByDepartmentId(int departmentId) throws SQLException {

        List<User> users = new ArrayList<>();

        // Step 2: create sql statement
        String sql = "SELECT 	u.id, u.username, u.fullname,u.phone, u.email, p.`name` as project_name, u.created_date, d.`name` as department_name \n" +
                "FROM		`user` u \n" +
                "LEFT JOIN	department d ON d.id = u.department_id \n" +
                "LEFT JOIN project p ON p.id = u.project_id \n" +
                "WHERE	d.id = ?	" ;
        PreparedStatement statement = jdbc.getConnection().prepareStatement(sql);

        // Step 3: execute SQL statement
        statement.setInt(1, departmentId);
        ResultSet resultSet = statement.executeQuery();

        // Step 4: handling result
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String fullname = resultSet.getString("fullname");
            String phone = resultSet.getString("phone");
            String email = resultSet.getString("email");
            Department department = new Department(resultSet.getString("department_name"));
            Project project = new Project(resultSet.getString("project_name"));
            LocalDate createdDate = resultSet.getDate("created_date").toLocalDate();

            users.add(new User(id, username, fullname, phone, email ,department,project, createdDate));
        }

        // Step 5: close connection
        jdbc.getConnection().close();

        return users;
    }

    public List<User> searchUserByKeyword(String keywordSearch) throws SQLException {

        List<User> users = new ArrayList<>();

        // Step 2: create sql statement
        String sql = "SELECT 	u.id, u.username, u.fullname, u.phone, u.email, p.`name` as project_name, u.created_date, d.`name` as department_name \n" +
                "FROM		`user` u \n" +
                "LEFT JOIN	department d ON d.id = u.department_id \n" +
                "LEFT JOIN project p ON p.id = u.project_id \n" +
                "WHERE		LOWER(u.fullname) LIKE ? OR LOWER(d.`name`) LIKE ? OR u.phone LIKE ? OR LOWER(u.username) LIKE ? OR LOWER(u.email) LIKE ?\n" +
                "ORDER 	BY u.created_date DESC, u.id";
        PreparedStatement statement = jdbc.getConnection().prepareStatement(sql);

        // Step 3: execute SQL statement
        statement.setString(1, "%" + keywordSearch.toLowerCase() + "%");
        statement.setString(2, "%" + keywordSearch.toLowerCase() + "%");
        statement.setString(3, "%" + keywordSearch.toLowerCase() + "%");
        statement.setString(4, "%" + keywordSearch.toLowerCase() + "%");
        statement.setString(5, "%" + keywordSearch.toLowerCase() + "%");

        ResultSet resultSet = statement.executeQuery();

        // Step 4: handling result
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String fullname = resultSet.getString("fullname");
            String phone = resultSet.getString("phone");
            String email = resultSet.getString("email");
            Department department = new Department(resultSet.getString("department_name"));
            Project project = new Project(resultSet.getString("project_name"));
            LocalDate createdDate = resultSet.getDate("created_date").toLocalDate();

            users.add(new User(id, username, fullname, phone, email ,department,project, createdDate));
        }

        // Step 5: close connection
        jdbc.getConnection().close();

        return users;
    }

    public User login(String username, String password) throws SQLException {

        // Step 2: create sql statement
        String sql = "SELECT u.id, u.username, u.fullname,u.phone, u.email, u.`role`, u.created_date, d.`name` as department_name, p.`name` as project_name \n" +
                "FROM		`user` u \n" +
                "LEFT JOIN	department d ON d.id = u.department_id \n" +
                "LEFT JOIN project p ON p.id = u.project_id \n" +
                "WHERE		u.username = ? AND u.`password` = ?";
        PreparedStatement statement = jdbc.getConnection().prepareStatement(sql);

        // Step 3: execute SQL statement
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();

        // Step 4: handling result
        if (!resultSet.next()) {
            return null;
        }

        int id = resultSet.getInt("id");
        String fullname = resultSet.getString("fullname");
        String phone = resultSet.getString("phone");
        String email = resultSet.getString("email");
        Department department = new Department(resultSet.getString("department_name"));
        Project project = new Project(resultSet.getString("project_name"));
        LocalDate createdDate = resultSet.getDate("created_date").toLocalDate();
        User.Role outputRole = User.Role.valueOf(resultSet.getString("role").toUpperCase());

        // Step 5: close connection
        jdbc.getConnection().close();

        return new User(id, username, fullname, phone, email ,department,project, outputRole, createdDate);

    }
    public void insertUser(String fullname, String phone, String email, String password , String role, int departmentId , int projectId ) throws SQLException {

        String sql = "INSERT INTO user(fullname, phone, email, username, password, `role`, department_id, project_id)  VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement statement = jdbc.getConnection().prepareStatement(sql);

        // Step 3: execute SQL statement
        statement.setString(1, fullname);
        statement.setString(2,phone);
        statement.setString(3,email);
        statement.setString(4,email.substring(0,email.indexOf("@")));
        statement.setString(5,password);
        statement.setString(6, role);
        statement.setInt(7,departmentId);
        statement.setInt(8,projectId);
        statement.executeUpdate();

        // Step 4: close connection
        jdbc.getConnection().close();
        System.out.println("Creat user completed!!! ");
    }
    public List<User> getAllUserByRole(String role) throws SQLException {

        List<User> users = new ArrayList<>();

        // Step 2: create sql statement
        String sql = "SELECT 	u.id, u.username, u.fullname, u.phone, u.email, u.`role`, u.created_date, d.`name` as department_name, p.`name` as project_name  \n" +
                "FROM		`user` u \n" +
                "LEFT JOIN	department d ON d.id = u.department_id \n" +
                "LEFT JOIN	project p ON p.id = u.project_id \n" +
                "WHERE		LOWER(u.`role`) = ? \n" +
                "ORDER 	BY u.created_date DESC, u.id";
        PreparedStatement statement = jdbc.getConnection().prepareStatement(sql);

        // Step 3: execute SQL statement
        statement.setString(1, role.toLowerCase());
        ResultSet resultSet = statement.executeQuery();

        // Step 4: handling result
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String fullname = resultSet.getString("fullname");
            String phone = resultSet.getString("phone");
            String email = resultSet.getString("email");
            Department department = new Department(resultSet.getString("department_name"));
            Project project = new Project(resultSet.getString("project_name"));
            LocalDate createdDate = resultSet.getDate("created_date").toLocalDate();
            User.Role outputRole = User.Role.valueOf(resultSet.getString("role").toUpperCase());


            users.add(new User(id, username, fullname, phone, email ,department,project, outputRole, createdDate));
        }

        // Step 5: close connection
        jdbc.getConnection().close();

        return users;
    }

}
