package com.epam.lab.task25.dao;

import com.epam.lab.task25.tables.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO implements BaseDAO<Department> {

    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/DataBase1";
    private static final String user = "root";
    private static final String password = "poloua123";

    private static final String DELETE = "DELETE FROM department WHERE dept_no=?";
    private static final String FIND_ALL = "SELECT * FROM department ORDER BY dept_no";
    private static final String FIND_BY_ID = "SELECT * FROM department WHERE dept_no=?";
    private static final String FIND_BY_NAME = "SELECT * FROM department WHERE dept_name=?";
    private static final String INSERT = "INSERT INTO department(dept_no, dept_name, location) VALUES(?, ?, ?)";
    private static final String UPDATE = "UPDATE department SET location=? WHERE dept_no=?";


    public int delete(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, id);

            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    public List<Department> findAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Department> list = new ArrayList<Department>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_ALL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Department department = new Department();
                department.setDept_no(rs.getInt("dept_no"));
                department.setDept_name(rs.getString("dept_name"));
                department.setLocation(rs.getString("location"));

                list.add(department);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }

        return list;
    }

    public Department findById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_BY_ID);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Department department = new Department();
                department.setDept_no(rs.getInt("dept_no"));
                department.setDept_name(rs.getString("dept_name"));
                department.setLocation(rs.getString("location"));

                return department;
            } else {
                return null;
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    public Department findByName(String name) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_BY_NAME);
            stmt.setString(1, name);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Department department = new Department();
                department.setDept_no(rs.getInt("dept_no"));
                department.setDept_name(rs.getString("dept_name"));
                department.setLocation(rs.getString("location"));

                return department;
            } else {
                return null;
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    public int insert(Department department) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, department.getDept_no());
            stmt.setString(2, department.getDept_name());
            stmt.setString(3, department.getLocation());


            return stmt.executeUpdate();
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    public int update(int deptNo, String name, String location) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, location);
            stmt.setInt(2, deptNo);


            return stmt.executeUpdate();
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(jdbcUrl, user, password);
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    private static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
