package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTable {

    public void create() {
        String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "admin";

        try {
            Connection c = DriverManager.getConnection(url, user, pass);
            Statement s = c.createStatement();
            s.execute("CREATE TABLE `linkedpurchaselist` " +
                    "(student_id INTEGER, " +
                    "course_id INTEGER)");
            s.close();
            c.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}

