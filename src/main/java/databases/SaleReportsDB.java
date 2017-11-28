package databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Menu;
import models.SaleReport;

import java.sql.*;
import java.text.ParseException;

public class SaleReportsDB {

    private static SaleReportsDB saleReportSelf = null;

    private SaleReportsDB(){}

    public static SaleReportsDB getSelf(){
        if (saleReportSelf == null){
            return new SaleReportsDB();
        }
        return saleReportSelf;
    }

    public ObservableList loadSaleReports() throws ClassNotFoundException, SQLException, ParseException {
        ObservableList<SaleReport> saleReports = FXCollections.observableArrayList();
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:SaleReport.db";
        Connection conn = DriverManager.getConnection(dbURL);
        if (conn != null) {
            String query = "select * from saleReports";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int idMenu = resultSet.getInt(2);
                String typeFood = resultSet.getString(3);
                String nameFood = resultSet.getString(4);
                String date = resultSet.getString(5);
                int quantity = resultSet.getInt(6);
                double cost = resultSet.getDouble(7);
                double price = resultSet.getDouble(8);
                double total = resultSet.getDouble(9);
                saleReports.add(new SaleReport(id, new Menu(idMenu,typeFood,nameFood,price,cost), date,quantity , total));
            }
            conn.close();
        }
        return saleReports;
    }

    public void writeSaleReport(SaleReport saleReport){
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:SaleReport.db";
            Connection connection = DriverManager.getConnection(dbURL);
            boolean have = false;
            if (connection != null) {
                String query = "select * from saleReports where nameFood like \'" + saleReport.getNameFood() + "\' and date like \'" + saleReport.getDate() + "\'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    have = true;
                    break;
                }
                String query1;
                if (have == false){
                    query1 = "insert into saleReports(idMenu, type, nameFood, date, quantity, price, cost, total) values (\'" + saleReport.getIdMenu() + "\' , \'" + saleReport.getType() + "\', \'" + saleReport.getNameFood() + "\',\'" + saleReport.getDate() + "\',\'" + saleReport.getQuantity() + "\',\'" + saleReport.getPrice() + "\',\'" + saleReport.getCost() + "\',\'" + saleReport.getPrice()  + "')";
                } else {
                    query1 = "update saleReports set quantity = quantity+1, total = total+price where nameFood == \'" + saleReport.getNameFood() + "\'";
                }
                PreparedStatement p = connection.prepareStatement(query1);
                p.executeUpdate();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDB(int id) {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:SaleReport.db";
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Delete from saleReports where id == \'" + id + "\'";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void writeReceipt(String str){
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:SaleReport.db";
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "insert into receipt(detail) values ('" + str + "')";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
