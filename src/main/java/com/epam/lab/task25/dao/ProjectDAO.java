package com.epam.lab.task25.dao;

import com.epam.lab.task25.tables.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProjectDAO implements BaseDAO<Project> {
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/DataBase1";
    private static final String user = "root";
    private static final String password = "poloua123";

    private static final String DELETE = "DELETE FROM project WHERE budget=?";
    private static final String FIND_ALL = "SELECT * FROM project ORDER BY project_no";
    private static final String FIND_BY_ID = "SELECT * FROM project WHERE budget=?";
    private static final String FIND_BY_NAME = "SELECT * FROM project WHERE project_name=?";
    private static final String INSERT = "INSERT INTO project(project_no, project_name, budget) VALUES(?, ?, ?)";
    private static final String UPDATE = "UPDATE project SET project_name=? WHERE budget=?";


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

    public List<Project> findAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Project> list = new ArrayList<Project>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_ALL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Project project = new Project();
                project.setProject_no(rs.getString("project_no"));
                project.setProject_name(rs.getString("project_name"));
                project.setBudget(rs.getInt("budget"));

                list.add(project);
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

    public Project findById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_BY_ID);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Project project = new Project();
                project.setProject_no(rs.getString("project_no"));
                project.setProject_name(rs.getString("project_name"));
                project.setBudget(rs.getInt("budget"));

                return project;
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

    public Project findByName(String name) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_BY_NAME);
            stmt.setString(1, name);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Project project = new Project();
                project.setProject_no(rs.getString("project_no"));
                project.setProject_name(rs.getString("project_name"));
                project.setBudget(rs.getInt("budget"));

                return project;
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

    public int insert(Project project) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, project.getProject_no());
            stmt.setString(2, project.getProject_name());
            stmt.setInt(3, project.getBudget());


            return stmt.executeUpdate();
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    public int update(int budget, String projectNo, String projectName) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, projectName);
            stmt.setInt(2, budget);


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
