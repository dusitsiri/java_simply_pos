package models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class SaleReport {

    private SimpleIntegerProperty id = new SimpleIntegerProperty(0);
    private Menu menu;
    private String date;
    private SimpleIntegerProperty quantity = new SimpleIntegerProperty(0);
    private SimpleDoubleProperty total = new SimpleDoubleProperty(0);

    public SaleReport(int id ,Menu menu, String date, int quantity, double total){
        this.id.set(id);
        this.date = date;
        this.quantity.set(quantity);
        this.total.set(total);
        this.menu = menu;
    }

    public int getId() {
        return id.get();
    }

    public int getIdMenu() { return this.menu.getId(); }

    public String getType(){ return this.menu.getType();}

    public String getNameFood() { return this.menu.getNameFood(); }

    public double getPrice() {
        return this.menu.getPrice();
    }

    public int getQuantity() {
        return quantity.get();
    }

    public double getTotal() {
        return total.get();
    }

    public double getCost() {
        return this.menu.getCost();
    }

    public String getDate() {
        return date;
    }
}
