package com.restaurant.models;

public class Drink extends Item {

    private int quantity;
    private DrinkCategory drinkCategory;
    private ManufacturerCategory manufacturerCategory;

    public Drink(int id, String name, double price, String description, int quantity, DrinkCategory drinkCategory, ManufacturerCategory manufacturerCategory) {
        super(id, name, price, description);
        this.quantity = quantity;
        this.drinkCategory = drinkCategory;
        this.manufacturerCategory = manufacturerCategory;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDrinkCategory() {
        return drinkCategory.getName();
    }

    public String getManufacturerCategory() {
        return manufacturerCategory.getName();
    }
}
