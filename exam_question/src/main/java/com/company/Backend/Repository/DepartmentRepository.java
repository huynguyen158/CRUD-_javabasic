package com.company.Backend.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.company.Backend.Entity.Department;
import com.company.Backend.Utils.JDBCUtils;

public class DepartmentRepository {

    private JDBCUtils jdbc;

    public DepartmentRepository() {
        jdbc = new JDBCUtils();
    }

    public List<Department> getAllDepartments() throws SQLException {

        List<Department> departments = new ArrayList<>();

        // Step 2: create sql statement
        Statement statement = jdbc.getConnection().createStatement();
        String sql = "SELECT id, `name`, member_size, created_date\n" +
                "FROM department\n" +
                "ORDER BY `name` ASC";

        // Step 3: execute SQL statement
        ResultSet resultSet = statement.executeQuery(sql);

        // Step 4: handling result
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int memberSize = resultSet.getInt("member_size");
            LocalDate createdDate = resultSet.getDate("created_date").toLocalDate();
            departments.add(new Department(id, name, memberSize, createdDate));
        }

        // Step 5: close connection
        jdbc.getConnection().close();

        return departments;
    }

    public List<Department> getDetailDepartmentId(int departmentId) throws SQLException {

        List<Department> departments = new ArrayList<>();

        // Step 2: create sql statement
        Statement statement = jdbc.getConnection().createStatement();
        String sql = "SELECT * FROM department WHERE `id` = ?";

        // Step 3: execute SQL statement
        ResultSet resultSet = statement.executeQuery(sql);

        // Step 4: handling result
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int memberSize = resultSet.getInt("member_size");
            LocalDate createdDate = resultSet.getDate("created_date").toLocalDate();
            departments.add(new Department(id, name, memberSize, createdDate));
        }

        // Step 5: close connection
        jdbc.getConnection().close();

        return departments;
    }

    public boolean isDepartmentExistByName(String name) throws SQLException {
        // Step 2: create sql statement
        String sql = "SELECT 1 FROM department WHERE `name` = ?";
        PreparedStatement statement = jdbc.getConnection().prepareStatement(sql);

        // Step 3: execute SQL statement
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();

        // Step 4: handling result
        boolean isExits = resultSet.next();

        // Step 5: close connection
        jdbc.getConnection().close();

        return isExits;
    }

    public List<Department> getDepartmentHasNoEmployee() throws SQLException {
        List<Department> departments = new ArrayList<Department>();
        // Step 2: create sql statement
        Statement statement = jdbc.getConnection().createStatement();
        String sql = "SELECT * FROM department WHERE member_size = 0 ORDER BY id asc";

        // Step 3: execute SQL statement
        ResultSet resultSet = statement.executeQuery(sql);

        // Step 4: handling result
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int size = resultSet.getInt("member_size");
            LocalDate localDate = resultSet.getDate("created_date").toLocalDate();
            departments.add(new Department(id, name, size, localDate));
        }

        // Step 5: close connection
        jdbc.getConnection().close();

        return departments;
    }

    public boolean isDepartmentExistsByName(String departmentName) throws SQLException {
        // Step 2: create sql statement
        String sql = "SELECT 1 FROM department WHERE `name` = ?";
        PreparedStatement statement = jdbc.getConnection().prepareStatement(sql);

        // Step 3: execute SQL statement
        statement.setString(1, departmentName);
        ResultSet resultSet = statement.executeQuery();

        // Step 4: handling result
        boolean isExits = resultSet.next();

        // Step 5: close connection
        jdbc.getConnection().close();

        return isExits;
    }

    public boolean isDepartmentExistsById(int departmentId) throws SQLException {
        // Step 2: create sql statement
        String sql = "SELECT 1 FROM department WHERE id = ?";
        PreparedStatement statement = jdbc.getConnection().prepareStatement(sql);

        // Step 3: execute SQL statement
        statement.setInt(1, departmentId);
        ResultSet resultSet = statement.executeQuery();

        // Step 4: handling result
        boolean isExits = resultSet.next();

        // Step 5: close connection
        jdbc.getConnection().close();

        return isExits;
    }


}
