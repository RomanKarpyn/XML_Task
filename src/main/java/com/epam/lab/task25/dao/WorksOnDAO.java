package com.epam.lab.task25.dao;

import com.epam.lab.task25.tables.WorksOn;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorksOnDAO {

    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/DataBase1";
    private static final String user = "root";
    private static final String password = "poloua123";

    private static final String DELETE = "DELETE FROM works_on WHERE emp_no=?";
    private static final String FIND_ALL = "SELECT * FROM works_on ORDER BY emp_no";
    private static final String FIND_BY_ID = "SELECT * FROM works_on WHERE emp_no=?";
    private static final String FIND_BY_NAME = "SELECT * FROM works_on WHERE job=?";
    private static final String INSERT = "INSERT INTO works_on(emp_no, project_no, job, enter_date) VALUES(?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE works_on SET project_no=?, job=? WHERE emp_no=?";


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

    public List<WorksOn> findAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<WorksOn> list = new ArrayList<WorksOn>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_ALL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                WorksOn worksOn = new WorksOn();
                worksOn.setEmp_no(rs.getInt("emp_no"));
                worksOn.setProject_no(rs.getString("project_no"));
                worksOn.setJob(rs.getString("job"));
                worksOn.setEnter_date(rs.getDate("enter_date"));

                list.add(worksOn);
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

    public WorksOn findById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_BY_ID);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                WorksOn worksOn = new WorksOn();
                worksOn.setEmp_no(rs.getInt("emp_no"));
                worksOn.setProject_no(rs.getString("project_no"));
                worksOn.setJob(rs.getString("job"));
                worksOn.setEnter_date(rs.getDate("enter_date"));

                return worksOn;
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

    public WorksOn findByName(String name) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_BY_NAME);
            stmt.setString(1, name);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                WorksOn worksOn = new WorksOn();
                worksOn.setEmp_no(rs.getInt("emp_no"));
                worksOn.setProject_no(rs.getString("emp_fname"));
                worksOn.setJob(rs.getString("emp_lname"));
                worksOn.setEnter_date(rs.getDate("enter_date"));

                return worksOn;
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

    public int insert(WorksOn worksOn) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, worksOn.getEmp_no());
            stmt.setString(2, worksOn.getProject_no());
            stmt.setString(3, worksOn.getJob());
            stmt.setDate(4, worksOn.getEnter_date());


            return stmt.executeUpdate();
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    public int update(int empNo, String projectName, String job) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, projectName);
            stmt.setString(2, job);
            stmt.setInt(3, empNo);


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
