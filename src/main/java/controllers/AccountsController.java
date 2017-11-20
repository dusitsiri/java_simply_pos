package controllers;

import databases.AccountsDB;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.Accounts;

import java.io.IOException;
import java.util.Optional;

public class AccountsController {

    static AccountsDB accountsDB = new AccountsDB();

    @FXML
    private TableView<Accounts> accountsTableView;
    @FXML
    private Button deleteButton,editButton;

    public void initialize() {
        editButton.setDisable(true);
        deleteButton.setDisable(true);
        accountsTableView.setItems(accountsDB.loadAccounts());
        accountsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Accounts>() {
            @Override
            public void changed(ObservableValue<? extends Accounts> observable, Accounts oldValue, Accounts newValue) {
                editButton.setDisable(false);
                deleteButton.setDisable(false);
            }
        });
    }

    public void createAccount(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/account-add.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void deleteAccount() {
        if (accountsTableView.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete " +
                    accountsTableView.getSelectionModel().getSelectedItem().getFirstname() +" "+
                    accountsTableView.getSelectionModel().getSelectedItem().getLastname() + " ?",
                    ButtonType.OK, ButtonType.CANCEL);
            Optional optional = alert.showAndWait();
            if (optional.get() == ButtonType.OK) {
                accountsDB.deleteAccountsDB(accountsTableView.getSelectionModel().getSelectedItem().getId());
                accountsTableView.setItems(accountsDB.loadAccounts());
                deleteButton.setDisable(true);
                editButton.setDisable(true);
            }
        }
    }

    public void backOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void editAccount(ActionEvent event) throws IOException {
        if (accountsTableView.getSelectionModel().getSelectedItem() != null) {
            Button button = (Button) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/account-add.fxml"));
            stage.setScene(new Scene(loader.load()));
            CreateAccountController createAccountController = loader.getController();
            createAccountController.setEditAccounts(accountsTableView.getSelectionModel().getSelectedItem());
            stage.show();
        }
    }
}
