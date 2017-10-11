package models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Menu {
    private SimpleIntegerProperty id = new SimpleIntegerProperty(0);
    private SimpleStringProperty type = new SimpleStringProperty("");
    private SimpleStringProperty nameFood = new SimpleStringProperty("");
    private SimpleDoubleProperty price = new SimpleDoubleProperty(0);
    private SimpleDoubleProperty cost = new SimpleDoubleProperty(0);

//    public Menu(String type, String nameFood, int quantity, double price) {
//        setType(type);
//        setNameFood(nameFood);
//        setPrice(price);
//    }

    public Menu(int id, String type, String nameFood, double price, double cost) {
        setId(id);
        setType(type);
        setNameFood(nameFood);
        setPrice(price);
        this.cost.set(cost);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) { this.id.set(id); }

    public String getType(){ return type.get();}

    public void setType(String type) { this.type.set(type);}

    public String getNameFood() { return nameFood.get(); }

    public void setNameFood(String nameFood) {
        this.nameFood.set(nameFood);
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public double getCost() {
        return cost.get();
    }

    public void setCost(double cost) {
        this.cost.set(cost);
    }
//
//    public int getQuantity() { return quantity.get(); }
//
//    public void setQuantity(int quantity) { this.quantity.set(quantity); }
}
