package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML private Text warningText;
    @FXML private TextField userName;
    @FXML private PasswordField userPassword;

    public void loginOnClick(ActionEvent event) throws IOException{
        if (userName.getText().equals("1") && (userPassword.getText().equals("1"))){
            Button button = (Button) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/point-of-sale.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.show();
            warningText.setText("");
        } else if (userName.getText().equals("2") && (userPassword.getText().equals("2"))) {
            Button button = (Button) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader( getClass().getResource("/home.fxml" ));
            stage.setScene(new Scene(loader.load()));
            stage.show();
            warningText.setText("");
        }else {
            warningText.setText("Incorrect username and/or password!");
        }
    }
}
