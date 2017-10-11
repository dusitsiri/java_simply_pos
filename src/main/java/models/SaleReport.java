package models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaleReport {

    private SimpleIntegerProperty id = new SimpleIntegerProperty(0);
    private Menu menu;
    private Date date;
    private SimpleIntegerProperty quantity = new SimpleIntegerProperty(0);
    private SimpleDoubleProperty total = new SimpleDoubleProperty(0);

    public SaleReport(int id ,Menu menu, Date date, int quantity, double total){
        this.id.set(id);
        this.date = date;
        this.quantity.set(quantity);
        this.total.set(total);
        this.menu = menu;
    }

    public String getDate(){
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        String date = formatDate.format(this.date);
        return date.substring(6,10)+"-"+date.substring(3,5)+"-"+date.substring(0,2);
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
}
