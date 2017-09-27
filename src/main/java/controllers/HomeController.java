package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    public void posOnClick(ActionEvent event) throws IOException{
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/point-of-sale.fxml"));
        stage.setScene(new Scene(loader.load()));
        PointOfSaleController pointOfSaleController = loader.getController();
        pointOfSaleController.setCashier(false);
        pointOfSaleController.getLogoutButton().setText("Back");
        stage.show();
    }

    public void managementOnClick(ActionEvent event) throws IOException{
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/management.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void logoutOnClick(ActionEvent event) throws IOException{
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void employeesOnClick(ActionEvent event) {

    }

    public void reportsOnClick(ActionEvent event) {

    }
}
