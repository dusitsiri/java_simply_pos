package controllers;

import models.Menu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateSalesController {
    Menu menu;
    @FXML private MenuButton typeFoodButton;
    @FXML private MenuItem item1,item2,item3;
    @FXML private TextField nameFood,price,cost;

    public void typeFood(ActionEvent event) {
        if (event.getTarget().equals(item1)) {
            typeFoodButton.setText(item1.getText());
        } else if (event.getTarget().equals(item2)) {
            typeFoodButton.setText(item2.getText());
        } else {
            typeFoodButton.setText(item3.getText());
        }
    }

    public void setEditMenu(Menu menu){
        this.menu = menu;
        nameFood.setText(this.menu.getNameFood());
        price.setText(String.valueOf(this.menu.getPrice()));
        typeFoodButton.setText(this.menu.getType());
        cost.setText(String.valueOf(this.menu.getCost()));
    }

    public void saveItem(ActionEvent event){
        if (menu == null) {
            if (!nameFood.getText().isEmpty() && !price.getText().isEmpty() && !typeFoodButton.getText().equals("Type of Food") && !cost.getText().isEmpty()) {
                SalesController.menuDB.saveDB(SalesController.menuDB.getCreateID(), typeFoodButton.getText(), nameFood.getText(), Double.parseDouble(price.getText()), Double.parseDouble(cost.getText()));
                nameFood.setText("");
                price.setText("");
                cost.setText("");
                typeFoodButton.setText("Type of Food");
                backToManagementWindow(event);
            }

        } else {
            menu.setNameFood(nameFood.getText());
            menu.setPrice(Double.parseDouble(price.getText()));
            menu.setCost(Double.parseDouble(cost.getText()));
            menu.setType(typeFoodButton.getText());
            SalesController.menuDB.editDB(this.menu.getId(), this.menu.getType(), this.menu.getNameFood(), this.menu.getPrice(), this.menu.getCost());
            backToManagementWindow(event);
        }
    }

    public void cancelToMenu(ActionEvent event){
        backToManagementWindow(event);
    }

    private void backToManagementWindow(ActionEvent event){
        Button cancelToMenu = (Button) event.getSource();
        Stage stage = (Stage) cancelToMenu.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sales.fxml"));
        try {
            stage.setScene(new Scene(loader.load(), 1080, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
