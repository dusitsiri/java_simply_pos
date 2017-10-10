package databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.SaleReport;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
                String typeFood = resultSet.getString(1);
                String nameFood = resultSet.getString(2);
                Double price = resultSet.getDouble(3);
                String date = resultSet.getString(4);
                String time = resultSet.getString(5);

                int day = Integer.parseInt(date.substring(8,10));
                int month = Integer.parseInt(date.substring(5,7));
                int year = Integer.parseInt(date.substring(0,4));

                DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                Date dateGen = dateTimeFormat.parse(day+"/"+month+"/"+year+" "+time);

                saleReports.add(new SaleReport(typeFood, nameFood, price, dateGen));
            }
            //close connection
            conn.close();
        }
        return saleReports;
    }

    public void writeSaleReport(SaleReport saleReport){
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:SaleReport.db";
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "insert into saleReports(type, nameFood, price, date, time) values (\'" + saleReport.getType() + "\' , \'" + saleReport.getNameFood() + "\',\'" + saleReport.getPrice() + "\',\'" + saleReport.getDate()  + "\',\'" + saleReport.getTime() + "')";
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
