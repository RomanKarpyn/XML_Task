package com.epam.lab.task4;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MetaDataInfo {

    private static final Logger log = Logger.getLogger(MetaDataInfo.class);

    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/DataBase1";
    private static final String user = "root";
    private static final String password = "poloua123";

    public static void main(String[] args) {

        Connection conn = null;

        {
            try {
                conn = DriverManager.getConnection(jdbcUrl, user, password);
                DatabaseMetaData databaseMetaData = conn.getMetaData();
                log.info("Product name: " + databaseMetaData.getDatabaseProductName());
                log.info("Product version: " + databaseMetaData.getDatabaseProductVersion());

                log.info("JDBC driver name: " + databaseMetaData.getDriverName());
                log.info("JDBC driver version: " + databaseMetaData.getDriverVersion());

            } catch (SQLException e) {
                log.warn("Connection failed");
            }
        }
    }
}
