package com.epam.lab.task25.dao;

import com.epam.lab.task25.Main;
import com.epam.lab.task25.tables.Employee;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements BaseDAO<Employee> {

    private static final Logger log = Logger.getLogger(Main.class);

    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/DataBase1";
    private static final String user = "root";
    private static final String password = "poloua123";

    private static final String DELETE = "DELETE FROM employee WHERE emp_no=?";
    private static final String FIND_ALL = "SELECT * FROM employee ORDER BY emp_no";
    private static final String FIND_BY_ID = "SELECT * FROM employee WHERE emp_no=?";
    private static final String FIND_BY_NAME = "SELECT * FROM employee WHERE emp_fname=?";
    private static final String INSERT = "INSERT INTO employee(emp_no, emp_fname, emp_lname, dept_no, emp_live) VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE employee SET emp_fname=?, emp_lname=? WHERE emp_no=?";


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

    public List<Employee> findAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Employee> list = new ArrayList<Employee>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_ALL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmp_no(rs.getInt("emp_no"));
                employee.setEmp_fname(rs.getString("emp_fname"));
                employee.setEmp_lname(rs.getString("emp_lname"));
                employee.setDept_no(rs.getInt("dept_no"));
                employee.setEmp_live(rs.getString("emp_live"));

                list.add(employee);
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

    public Employee findById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_BY_ID);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Employee employee = new Employee();
                employee.setEmp_no(rs.getInt("emp_no"));
                employee.setEmp_fname(rs.getString("emp_fname"));
                employee.setEmp_lname(rs.getString("emp_lname"));
                employee.setDept_no(rs.getInt("dept_no"));
                employee.setEmp_live(rs.getString("emp_live"));

                return employee;
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

    public Employee findByName(String name) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_BY_NAME);
            stmt.setString(1, name);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Employee employee = new Employee();
                employee.setEmp_no(rs.getInt("emp_no"));
                employee.setEmp_fname(rs.getString("emp_fname"));
                employee.setEmp_lname(rs.getString("emp_lname"));
                employee.setDept_no(rs.getInt("dept_no"));
                employee.setEmp_live(rs.getString("emp_live"));

                return employee;
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

    public int insert(Employee employee) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, employee.getEmp_no());
            stmt.setString(2, employee.getEmp_fname());
            stmt.setString(3, employee.getEmp_lname());
            stmt.setInt(4, employee.getDept_no());
            stmt.setString(5, employee.getEmp_live());


            return stmt.executeUpdate();
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    public int update(int empNo,String empFname, String empLname) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, empFname);
            stmt.setString(2, empLname);
            stmt.setInt(3,empNo);


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

    public void deleteAndReplaceEmployyesToAnother(int newEmp_no, int oldEmp_no) {
        Connection conn = null;

        String updateSql = "update employee set emp_no=? where emp_no=?";
        String deleteSql = "delete from works_on where emp_no=?";
        try {
                conn = getConnection();
            PreparedStatement updateStatement = conn.prepareStatement(updateSql);
            updateStatement.setInt(1,newEmp_no);
            updateStatement.setInt(2,oldEmp_no);
            updateStatement.executeUpdate();
            PreparedStatement deleteStatement = conn.prepareStatement(deleteSql);
            deleteStatement.setInt(1, oldEmp_no);
            deleteStatement.execute();
            updateStatement.close();
            deleteStatement.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            close(conn);
        }
    }

}
