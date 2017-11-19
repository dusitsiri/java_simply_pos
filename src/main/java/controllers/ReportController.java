package controllers;

import databases.SaleReportsDB;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    @FXML private Button deleteButton;
    @FXML private TableView<SaleReport> reportTableView;
    private SaleReportsDB saleReportDB = SaleReportsDB.getSelf();

    public void initialize() throws ParseException, SQLException, ClassNotFoundException {
        deleteButton.setDisable(true);
        reportTableView.setItems(saleReportDB.loadSaleReports());
        reportTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SaleReport>() {
            @Override
            public void changed(ObservableValue<? extends SaleReport> observable, SaleReport oldValue, SaleReport newValue) {
                deleteButton.setDisable(false);
            }
        });
    }

    public void backOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void deleteItem() throws ParseException, SQLException, ClassNotFoundException {
        if (reportTableView.getSelectionModel().getSelectedItem() != null) {
            saleReportDB.deleteDB(reportTableView.getSelectionModel().getSelectedItem().getId());
            reportTableView.setItems(saleReportDB.loadSaleReports());
            deleteButton.setDisable(true);
        }
    }
}
