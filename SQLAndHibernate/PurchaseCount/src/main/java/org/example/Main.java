package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "admin";

        try {
            Connection c = DriverManager.getConnection(url, user, pass);
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(
                    "SELECT course_name, COUNT(*), MAX(MONTH(subscription_date)), MIN(MONTH(subscription_date))\n" +
                    "FROM purchaselist \n" +
                    "group by course_name\n" +
                    "order by course_name;");

            while(rs.next()) {
                System.out.println(rs.getString("course_name")
                    + " - " + "Среднее количество покупок в месяц: "
                    + averageMonthPurchase(rs));
            }


            rs.close();
            s.close();
            c.close();
        } catch(Exception ex) {
                ex.printStackTrace();
        }
    }

    public static String averageMonthPurchase(ResultSet rs) throws SQLException {
        double x = (double) Integer.parseInt(rs.getString("COUNT(*)")) /
                (Integer.parseInt(rs.getString("MAX(MONTH(subscription_date))"))
                 - Integer.parseInt(rs.getString("MIN(MONTH(subscription_date))")) + 1);

        return String.valueOf(x);
    }
}