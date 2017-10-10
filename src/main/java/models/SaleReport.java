package models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaleReport {
    private SimpleIntegerProperty id = new SimpleIntegerProperty(0);
    private SimpleStringProperty type = new SimpleStringProperty("");
    private SimpleStringProperty nameFood = new SimpleStringProperty("");
    private SimpleDoubleProperty price = new SimpleDoubleProperty(0);
    private Date date;

    public SaleReport(String type, String nameFood, double price, Date date){
        this.type.set(type);
        this.nameFood.set(nameFood);
        this.price.set(price);
        this.date = date;
    }

    public String getDate(){
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        String date = formatDate.format(this.date);
        return date.substring(6,10)+"-"+date.substring(3,5)+"-"+date.substring(0,2);
    }

    public String getTime(){
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
        String time = formatTime.format(this.date.getTime());
        return time;
    }

    public int getId() {
        return id.get();
    }

    public String getType(){ return type.get();}

    public String getNameFood() { return nameFood.get(); }

    public double getPrice() {
        return price.get();
    }
}
