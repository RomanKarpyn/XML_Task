package com.epam.lab.task4;

import org.apache.log4j.Logger;

import java.sql.*;

public class TableInfo {

    private static final Logger log = Logger.getLogger(TableInfo.class);

    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/DataBase1";
    private static final String user = "root";
    private static final String password = "poloua123";

    public static void main(String[] args) {

        String catalog = null;
        String schemaPatern = null;
        String tableNamePattern = null;
        String columnNamePattern = null;
        String[] types = null;

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(jdbcUrl, user, password);
            DatabaseMetaData databaseMetaData = conn.getMetaData();
            log.info("List of tables: ");
            ResultSet resultSet = databaseMetaData.getTables(catalog, schemaPatern, tableNamePattern, types);

            while (resultSet.next()) {
                log.info(resultSet.getString("TABLE_NAME"));
            }
            log.info("");
            log.info("List of columns: ");

            ResultSet resultSetDepartment = databaseMetaData.getColumns(catalog, schemaPatern, "department", columnNamePattern);
            ResultSet resultSetEmployee = databaseMetaData.getColumns(catalog, schemaPatern, "employee", columnNamePattern);
            ResultSet resultSetProject = databaseMetaData.getColumns(catalog, schemaPatern, "project", columnNamePattern);
            ResultSet resultSetWorks_on = databaseMetaData.getColumns(catalog, schemaPatern, "works_on", columnNamePattern);

            log.info("");
            log.info("Columns department: ");
            while (resultSetDepartment.next()) {
                log.info(resultSetDepartment.getString("COLUMN_NAME"));
                log.info(resultSetDepartment.getString("DATA_TYPE"));
            }
            log.info("");
            log.info("Columns employee: ");
            while (resultSetEmployee.next()) {
                log.info(resultSetEmployee.getString("COLUMN_NAME"));
                log.info(resultSetEmployee.getString("DATA_TYPE"));
            }
            log.info("");
            log.info("Columns project: ");
            while (resultSetProject.next()) {
                log.info(resultSetProject.getString("COLUMN_NAME"));
                log.info(resultSetProject.getString("DATA_TYPE"));
            }
            log.info("");
            log.info("Columns works_on: ");
            while (resultSetWorks_on.next()) {
                log.info(resultSetWorks_on.getString("COLUMN_NAME"));
                log.info(resultSetWorks_on.getString("DATA_TYPE"));
            }
        } catch (SQLException e) {
            log.warn("Connection failed");
        }
    }
}
