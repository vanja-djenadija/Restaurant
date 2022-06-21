package com.restaurant.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Menu {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty dateFrom;
    private SimpleStringProperty dateTo;

    public Menu(int id, String name, String dateFrom, String dateTo) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.dateFrom = new SimpleStringProperty(dateFrom);
        this.dateTo = new SimpleStringProperty(dateTo);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDateFrom() {
        return dateFrom.get();
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom.set(dateFrom);
    }

    public String getDateTo() {
        return dateTo.get();
    }


    public void setDateTo(String dateTo) {
        this.dateTo.set(dateTo);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name=" + name +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}
