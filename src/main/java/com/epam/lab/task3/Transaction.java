package com.epam.lab.task3;

import com.epam.lab.task2.CrudOperations;
import com.epam.lab.task4.MetaDataInfo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Scanner;

public class Transaction {

    private static final Logger log = Logger.getLogger(Transaction.class);

    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/DataBase";
    private static final String user = "root";
    private static final String password = "poloua123";

    public static void main(String[] args) {

        Connection conn = null;
        Statement statement = null;

        {
            try {
                conn = DriverManager.getConnection(jdbcUrl, user, password);
                conn.setAutoCommit(false);
                log.info("Table before\n");
                executingSelectStatement(conn);

                statement = conn.createStatement();
                statement.executeUpdate("delete from works_on where job=\"manager\"");

                statement.executeUpdate("update works_on set project_no=\"p3\" where job=\"clerk\"");
                log.info("\n>> Transaction are ready.\n");

                boolean ok = askUserIfOkToSave();

                if(ok){
                    conn.commit();
                    log.info("\n>> Transaction completed.\n");
                } else {
                    conn.rollback();
                    System.out.println("\n>> Transaction rolled back.\n");
                }

                log.info("Table after\n");
                executingSelectStatement(conn);

            } catch (SQLException e) {
                log.info("Connection failed");
            }
        }
    }

    private static boolean askUserIfOkToSave () {
        Scanner sc = new Scanner(System.in);

        log.info("Is it ok to save? hit yes/no: ");
        String hit = sc.nextLine();

        sc.close();
        return hit.equalsIgnoreCase("yes");
    }

    public static void executingSelectStatement(Connection conn) {
        String sql = "SELECT * FROM works_on";

        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            int count = 0;
            while (result.next()) {
                String emp_no = result.getString(1);
                String project_no = result.getString(2);
                String job = result.getString(3);
                String enter_date = result.getString(4);
                String output = "works_on #%d: %s - %s - %s - %s";
                log.info(String.format(output, ++count, emp_no, project_no, job, enter_date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
