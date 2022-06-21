package com.restaurant.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DrinkCategory {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;

    public DrinkCategory(int id, String name) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
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

    @Override
    public String toString() {
        return "DrinkCategory{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
