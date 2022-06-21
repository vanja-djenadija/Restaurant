package com.restaurant.models;

public class Food extends Item {
    private String recipe;
    private String portionSize;
    private FoodCategory foodCategory;

    public Food(int id, String name, double price, String description, String recipe, String portionSize, FoodCategory foodCategory) {
        super(id, name, price, description);
        this.recipe = recipe;
        this.portionSize = portionSize;
        this.foodCategory = foodCategory;
    }

    public String getRecipe() {
        return recipe;
    }

    public String getPortionSize() {
        return portionSize;
    }

    public String getFoodCategory() {
        return foodCategory.getName();
    }
}
