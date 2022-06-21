package com.restaurant.models;

public class OrderItem extends Item {
    private int quantity;

    public OrderItem() {
        super();
    }

    public OrderItem(Item item) {
        this(item.getId(), item.getName(), item.getPrice(), item.getDescription(), 1);
    }

    public OrderItem(int id, String name, double price, String description, int quantity) {
        super(id, name, price, description);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        quantity--;
    }
}
