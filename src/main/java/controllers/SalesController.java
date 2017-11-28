package controllers;

import databases.MenuDB;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import models.Menu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class SalesController implements Initializable {
    static MenuDB menuDB = new MenuDB();
    @FXML private TableView<Menu> tableView;
    @FXML private Button deleteButton,editButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        editButton.setDisable(true);
        deleteButton.setDisable(true);
        tableView.setItems(menuDB.loadMenu());
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Menu>() {
            @Override
            public void changed(ObservableValue<? extends Menu> observable, Menu oldValue, Menu newValue) {
                editButton.setDisable(false);
                deleteButton.setDisable(false);
            }
        });
    }

    public void createItem(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sale-add.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void deleteItem() {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete " +
                    tableView.getSelectionModel().getSelectedItem().getNameFood() + " ?",
                    ButtonType.OK, ButtonType.CANCEL);
            Optional optional = alert.showAndWait();
            if (optional.get() == ButtonType.OK) {
                menuDB.deleteDB(tableView.getSelectionModel().getSelectedItem().getId());
                tableView.setItems(menuDB.loadMenu());
                deleteButton.setDisable(true);
                editButton.setDisable(true);
            }
        }
    }

    public void editItem(ActionEvent event) throws IOException {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            Button button = (Button) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sale-add.fxml"));
            stage.setScene(new Scene(loader.load()));
            CreateSalesController createSalesController = loader.getController();
            createSalesController.setEditMenu(tableView.getSelectionModel().getSelectedItem());
            stage.show();
        }
    }

    public void backOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
}
