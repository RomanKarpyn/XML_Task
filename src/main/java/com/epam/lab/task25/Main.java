package com.epam.lab.task25;

import com.epam.lab.task25.dao.DepartmentDAO;
import com.epam.lab.task25.dao.EmployeeDAO;
import com.epam.lab.task25.dao.ProjectDAO;
import com.epam.lab.task25.dao.WorksOnDAO;
import com.epam.lab.task25.tables.Department;
import com.epam.lab.task25.tables.Employee;
import com.epam.lab.task25.tables.Project;
import com.epam.lab.task25.tables.WorksOn;
import org.apache.log4j.Logger;

import java.sql.*;


public class Main {

    private static final Logger log = Logger.getLogger(Main.class);

    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/DataBase1";
    private static final String user = "root";
    private static final String password = "poloua123";

    private static final String TABLE_DEPARTMENT = "CREATE TABLE `department` (\n" +
            " `dept_no` int(11) NOT NULL AUTO_INCREMENT,\n" +
            " `dept_name` varchar(45) NOT NULL,\n" +
            " `location` varchar(45) NOT NULL,\n" +
            " PRIMARY KEY (`dept_no`),\n" +
            " KEY `depEmplo_idx` (`dept_name`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci AUTO_INCREMENT=1";
    private static final String TABLE_EMPLOYEE = "CREATE TABLE employee (\n" +
            "emp_no INT AUTO_INCREMENT NOT NULL,\n" +
            "emp_fname NVARCHAR(45) NOT NULL,\n" +
            "emp_lname NVARCHAR(45) NOT NULL,\n" +
            "dept_no INT NOT NULL,\n" +
            "emp_live VARCHAR(45) NOT NULL,\n" +
            "primary key (emp_no)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;";
    private static final String TABLE_PROJECT = "CREATE TABLE project (\n" +
            "project_no VARCHAR(45)  NOT NULL,\n" +
            "project_name VARCHAR(45) NOT NULL,\n" +
            "budget INT AUTO_INCREMENT NOT NULL,\n" +
            "primary key (budget)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;";
    private static final String TABLE_WORKS_ON = "CREATE TABLE `works_on` (\n" +
            "emp_no INT AUTO_INCREMENT NOT NULL,\n" +
            "project_no VARCHAR(45) NOT NULL,\n" +
            "job VARCHAR(45),\n" +
            "enter_date DATE NOT NULL,\n" +
            "primary key (emp_no)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;";

    private static final String INSERT_DEPARTMENT = "insert into department(dept_no, dept_name, location) values\n" +
            " (1, 'research', 'Dallas'),\n" +
            " (2, 'accounting', 'Seattle'),\n" +
            " (3, 'marketing', 'Dallas');";
    private static final String INSERT_EMPLOYEE = "insert into employee(emp_no, emp_fname, emp_lname, dept_no, emp_live) values\n" +
            " (2581, 'Elke', 'Hansel', 2, 'Seattle'),\n" +
            " (23, N'Роман', N'Карпин', 1, N'Львiв'),\n" +
            " (10102, 'Ann', 'jones', 3, 'Roma'),\n" +
            " (18316, 'John', 'Barrimore', 1, 'Barselona'),\n" +
            " (25348, 'Matthew', 'Smith', 1, 'Seattle'),\n" +
            " (25871, 'Elke', 'Hansel', 3, 'Dallas'),\n" +
            " (29346, 'James', 'James', 2, 'Lviv'),\n" +
            " (28559, 'Sybill', 'Moser', 1, 'Napoli');";

    private static final String INSERT_PROJECT = "insert into project(project_no, project_name, budget) values\n" +
            " ('p1', 'Apollo', 120000),\n" +
            " ('p2', 'Mercury', 186500),\n" +
            " ('p3', 'Marketing', 132452);";
    private static final String INSERT_WORKS_ON = "insert into works_on(emp_no, project_no, job, enter_date) values\n" +
            " (2581, 'p2', 'manager', '2006-04-01'),\n" +
            " (2345, 'p1', 'clerk', '2005-06-23'),\n" +
            " (10102, 'p3', 'manager', '2006-08-04'),\n" +
            " (18316, 'p2', 'clerk', '2007-05-30'),\n" +
            " (25348, 'p3', NULL, '2009-03-11'),\n" +
            " (25871, 'p1', 'clerk', '2006-12-12'),\n" +
            " (29346, 'p2', NULL, '2007-03-15'),\n" +
            " (28559, 'p3', 'manager', '2004-09-27');";

    public static void main(String[] args) {

        createAndFillDataBase();

        log.info("Employee: ");
        Employee employee = new Employee(21, "Roooo", "Maaa", 5, "Lviv");
        EmployeeDAO employeeDAO = new EmployeeDAO();
        log.info("Delete");
        employeeDAO.delete(10102);
        log.info("Find all: " + employeeDAO.findAll());
        log.info("Find by id: " + employeeDAO.findById(21));
        log.info("Find by name: " + employeeDAO.findByName("mark"));
        employeeDAO.insert(employee);
        employeeDAO.update(21, "Roman", "Karpyn");
        employeeDAO.deleteAndReplaceEmployyesToAnother(2345, 4444);

        log.info("");
        log.info("Department: ");
        Department department = new Department(8, "mark", "Dallas");
        DepartmentDAO departmentDAO = new DepartmentDAO();
        log.info("Delete");
        departmentDAO.delete(3);
        log.info("Find all: " + departmentDAO.findAll());
        log.info("Find by id: " + departmentDAO.findById(3));
        log.info("Find by name: " + departmentDAO.findByName("mark"));
        departmentDAO.insert(department);
        departmentDAO.update(7, "mark", "Lviv");

        log.info("");
        log.info("Project: ");
        Project project = new Project("p5", "mark", 323232);
        ProjectDAO projectDAO = new ProjectDAO();
        log.info("Delete");
        projectDAO.delete(132452);
        log.info("Find all: " + projectDAO.findAll());
        log.info("Find by id: " + projectDAO.findById(186500));
        log.info("Find by name: " + projectDAO.findByName("Mercury"));
        projectDAO.insert(project);
        projectDAO.update(323232, "mark", "p3");

        log.info("");
        log.info("Works_on: ");
        Date date = new Date(2004 - 04 - 21);
        WorksOn worksOn = new WorksOn(5456, "grgrgr", "tretev", date);
        WorksOnDAO worksOnDAO = new WorksOnDAO();
        log.info("Delete");
        worksOnDAO.delete(32);
        log.info("Find all: " + worksOnDAO.findAll());
        log.info("Find by id: " + worksOnDAO.findById(10102));
        worksOnDAO.insert(worksOn);
        worksOnDAO.update(5456, "p4", "manager");
    }

    private static void createAndFillDataBase() {
        Connection conn = null;
        PreparedStatement stmt = null;

        String createDataBase = "CREATE DATABASE IF NOT EXISTS DataBase1";
        String using = "USE DataBase1";

        try {
            conn = DriverManager.getConnection(jdbcUrl, user, password);

            stmt = conn.prepareStatement(createDataBase);
            stmt.executeUpdate();
            stmt = conn.prepareStatement(using);
            stmt.executeUpdate();
            stmt = conn.prepareStatement(TABLE_DEPARTMENT);
            stmt.executeUpdate();
            stmt = conn.prepareStatement(TABLE_EMPLOYEE);
            stmt.executeUpdate();
            stmt = conn.prepareStatement(TABLE_PROJECT);
            stmt.executeUpdate();
            stmt = conn.prepareStatement(TABLE_WORKS_ON);
            stmt.executeUpdate();
            stmt = conn.prepareStatement(INSERT_DEPARTMENT);
            stmt.executeUpdate();
            stmt = conn.prepareStatement(INSERT_EMPLOYEE);
            stmt.executeUpdate();
            stmt = conn.prepareStatement(INSERT_PROJECT);
            stmt.executeUpdate();
            stmt = conn.prepareStatement(INSERT_WORKS_ON);
            stmt.executeUpdate();
            stmt.close();

        } catch (Exception e) {
            try {
                stmt = conn.prepareStatement("DROP DATABASE DataBase1");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new RuntimeException(e);
        }
    }
}

