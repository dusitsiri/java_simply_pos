package controllers;

import models.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.InputValue;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static controllers.ManagementController.edit;

public class PointOfSaleController implements Initializable{
    private InputValue inputValue = new InputValue();
    private double netBaht = 0;
    private double taxBaht = 0;
    private double totalBaht = 0;
    private boolean isCashier;

    @FXML
    public Label netLabel, cashLabel, totalLabel, texLabel, changeLabel;
    @FXML
    public Button payButton, oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton, sevenButton, eightButton, nineButton, zeroButton, deleteButton, doneButton, dotButton, logoutButton, backspaceButton;

    public PointOfSaleController() {
        isCashier = true;
    }

    public void handleBtnNumber0() {
        if (inputValue.addDot( "0" ).equals( "." )) {
            cashLabel.setText( inputValue.toString() );
        } else {
            appendNumberToInput( "0" );
        }
    }

    public void handleBtnNumber1() {
        if (inputValue.addDot( "1" ).equals( "." )) {
            cashLabel.setText( inputValue.toString() );
        } else {
            appendNumberToInput( "1" );
        }
    }

    public void handleBtnNumber2() {
        if (inputValue.addDot( "2" ).equals( "." )) {
            cashLabel.setText( inputValue.toString() );
        } else {
            appendNumberToInput( "2" );
        }
    }

    public void handleBtnNumber3() {
        if (inputValue.addDot( "3" ).equals( "." )) {
            cashLabel.setText( inputValue.toString() );
        } else {
            appendNumberToInput( "3" );
        }
    }

    public void handleBtnNumber4() {
        if (inputValue.addDot( "4" ).equals( "." )) {
            cashLabel.setText( inputValue.toString() );
        } else {
            appendNumberToInput( "4" );
        }
    }

    public void handleBtnNumber5() {
        if (inputValue.addDot( "5" ).equals( "." )) {
            cashLabel.setText( inputValue.toString() );
        } else {
            appendNumberToInput( "5" );
        }
    }

    public void handleBtnNumber6() {
        if (inputValue.addDot( "6" ).equals( "." )) {
            cashLabel.setText( inputValue.toString() );
        } else {
            appendNumberToInput( "6" );
        }
    }

    public void handleBtnNumber7() {
        if (inputValue.addDot( "7" ).equals( "." )) {
            cashLabel.setText( inputValue.toString() );
        } else {
            appendNumberToInput( "7" );
        }
    }

    public void handleBtnNumber8() {
        if (inputValue.addDot( "8" ).equals( "." )) {
            cashLabel.setText( inputValue.toString() );
        } else {
            appendNumberToInput( "8" );
        }
    }

    public void handleBtnNumber9() {
        if (inputValue.addDot( "9" ).equals( "." )) {
            cashLabel.setText( inputValue.toString() );
        } else {
            appendNumberToInput( "9" );
        }
    }
    public void handleBtnNumberDot() {
        inputValue.addDot( "." );
        cashLabel.setText( inputValue.toString() );
    }

    public void handleBtnCE() {
        inputValue.setCheckdot("");
        inputValue.setCheck1("");
        inputValue.setCheck2("");
        inputValue.getNumber();
        cashLabel.setText(inputValue.toString());
    }

    public void handleBtnDelete() {
        inputValue.deleteNumber();
        cashLabel.setText(inputValue.toString());
    }

    public void handleBtnLogout(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        if (isCashier){
            FXMLLoader loader = new FXMLLoader( getClass().getResource( "../login.fxml" ) );
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } else {
            FXMLLoader loader = new FXMLLoader( getClass().getResource( "../home.fxml" ) );
            stage.setScene(new Scene(loader.load()));
            stage.show();
        }
    }

    public void handleBtnDone() {
        if (cashLabel.getText().equals("")){
            cashLabel.setText("0.00");
        }
        if (Double.parseDouble(cashLabel.getText()) >= totalBaht){
            double enterBaht = Double.parseDouble(cashLabel.getText());
            double change = enterBaht-totalBaht;
            System.out.println("Enter : "+enterBaht);
            System.out.println("Total : "+totalBaht);
            System.out.println("Change : "+String.valueOf(change));
            System.out.println(changeLabel);
//            changeLabel.setText(String.format("%.2f",String.valueOf(change).toString())); error
            this.payButton.setDisable(false);
        }
    }

    private void appendNumberToInput(String n) {
        inputValue.appendNumber(n);
        cashLabel.setText(inputValue.toString());
    }

    @FXML
    private TableView<Menu> posTableView,customerOrderTableView;

    private ObservableList<Menu> listOrder = FXCollections.observableArrayList();

    public void bthPay(){
        listOrder = FXCollections.observableArrayList();
        customerOrderTableView.setItems(listOrder);
        netBaht = 0;
        taxBaht = 0;
        totalBaht = 0;
        netLabel.setText(String.format("%.2f",netBaht));
        texLabel.setText(String.format("%.2f",taxBaht));
        totalLabel.setText(String.format("%.2f",totalBaht));
        cashLabel.setText("");
        this.handleBtnCE();
        this.payButton.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.payButton.setDisable(true);
        posTableView.setItems(edit.loadMenu());
        posTableView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.SECONDARY) {
                    this.addMenuToOrder();
                }else{
                    this.addMenuToOrder();
                }
            }
            private void addMenuToOrder(){
                if (posTableView.getSelectionModel().getSelectedItem() != null){
                    listOrder.add(posTableView.getSelectionModel().getSelectedItem());
                    customerOrderTableView.setItems(listOrder);
                    double currentNetBaht = posTableView.getSelectionModel().getSelectedItem().getPrice();
                    double currentTaxBaht = (currentNetBaht*7)/100;
                    double currentTotalBaht = currentNetBaht+currentTaxBaht;
                    netBaht += currentNetBaht;
                    taxBaht += currentTaxBaht;
                    totalBaht += currentTotalBaht;
                    netLabel.setText(String.format("%.2f",netBaht));
                    texLabel.setText(String.format("%.2f",taxBaht));
                    totalLabel.setText(String.format("%.2f",totalBaht));
                }
            }
        });
        customerOrderTableView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.SECONDARY) {
                    this.deleteMenuOrder();
                } else {
                    this.deleteMenuOrder();
                }
            }
            private void deleteMenuOrder(){
                if (customerOrderTableView.getSelectionModel().getSelectedItem() != null){
                    double currentNetBaht = customerOrderTableView.getSelectionModel().getSelectedItem().getPrice();
                    double currentTaxBaht = (currentNetBaht*7)/100;
                    double currentTotalBaht = currentNetBaht+currentTaxBaht;
                    listOrder.remove(customerOrderTableView.getSelectionModel().getSelectedIndex());
                    customerOrderTableView.setItems(listOrder);
                    netBaht -= currentNetBaht;
                    taxBaht -= currentTaxBaht;
                    totalBaht -= currentTotalBaht;
                    netLabel.setText(String.format("%.2f",netBaht));
                    texLabel.setText(String.format("%.2f",taxBaht));
                    totalLabel.setText(String.format("%.2f",totalBaht));
                }
            }
        });
    }

    public void setCashier(boolean cashier) {
        isCashier = cashier;
    }

    public Button getLogoutButton() {
        return logoutButton;
    }
}
