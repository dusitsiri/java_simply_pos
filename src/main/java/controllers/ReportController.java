package controllers;

import databases.SaleReportsDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.SaleReport;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class ReportController {
    @FXML
    private TableView<SaleReport> tableView;
    private SaleReportsDB saleReportDB = SaleReportsDB.getSelf();

    public void initialize() throws ParseException, SQLException, ClassNotFoundException {
        tableView.setItems(saleReportDB.loadSaleReports());
    }

    public void backOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void deleteItem() {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            saleReportDB.deleteDB(tableView.getSelectionModel().getSelectedItem().getId());
            try {
                tableView.setItems(saleReportDB.loadSaleReports());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
