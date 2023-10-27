package de.hawk200014.SQL;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLManager {

    private String dburl = "";
    private void createNewDatabase(String fileName) {

        this.dburl = "jdbc:sqlite:" + System.getProperty("user.dir") + "/data/" + fileName;

        try {
            Connection conn = DriverManager.getConnection(dburl);
            if (conn != null) {
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createTables(){

    }



}
