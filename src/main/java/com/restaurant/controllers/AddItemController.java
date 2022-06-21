package com.restaurant.controllers;

import com.restaurant.dao.mysql.MenuDAOImpl;
import com.restaurant.models.*;
import com.restaurant.util.JavaFXUtil;
import com.restaurant.util.Util;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AddItemController {
    @FXML
    private Button saveButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private RadioButton foodRadioButton;

    @FXML
    private RadioButton drinkRadioButton;

    @FXML
    private BorderPane borderPane;

    @FXML
    private ToggleGroup group;

    private FoodInfoController foodInfoController;

    private DrinkInfoController drinkInfoController;


    public void showFoodInfo() {
        Parent root = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/FoodInfo.fxml"));
            root = fxmlLoader.load();
            foodInfoController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPane.setCenter(root);
        foodInfoController.setFoodCategories();
        foodInfoController.setPortionSizeCategories();
    }

    public void showDrinkInfo() {
        Parent root = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/DrinkInfo.fxml"));
            root = fxmlLoader.load();
            drinkInfoController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPane.setCenter(root);
        drinkInfoController.setDrinkCategories();
        drinkInfoController.setManufacturerCategories();
    }

    public void addMenuItem() {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        Item item = null;
        try {
            if (checkIfInputValuesValid()) {
                String name = nameTextField.getText();
                double price;
                try {
                    price = Double.parseDouble(priceTextField.getText());
                } catch (NumberFormatException e) {
                    JavaFXUtil.showAlert(Alert.AlertType.ERROR, Util.ERROR, "Invalid price format.");
                    return;
                }
                String description = descriptionTextField.getText();

                if (foodRadioButton.isSelected()) {
                    String recipe = foodInfoController.getRecipe();
                    String portion = foodInfoController.getPortion();
                    String foodCategory = foodInfoController.getFoodCategory();
                    item = new Food(0, name, price, description, recipe, portion, new FoodCategory(0, foodCategory));
                } else if (drinkRadioButton.isSelected()) {
                    String drinkCategory = drinkInfoController.getDrinkCategory();
                    String manufacturerCategory = drinkInfoController.getManufacturerCategory();
                    int quantity = drinkInfoController.getQuantity();
                    item = new Drink(0, name, price, description, quantity, new DrinkCategory(0, drinkCategory), new ManufacturerCategory(0, manufacturerCategory));
                }
                boolean success = new MenuDAOImpl().addItem(item);
                JavaFXUtil.showInfoAlert(success, Util.INFO, Util.ADD_SUCCESS, Util.ADD_FAILURE);
                stage.close();
            }
        } catch (Exception ex) {
            JavaFXUtil.showAlert(Alert.AlertType.ERROR, Util.ERROR, Util.ERROR);
        }
    }


    private boolean checkIfInputValuesValid() {
        if (nameTextField.getText().equals("") || priceTextField.getText().equals("")) {
            JavaFXUtil.showAlert(Alert.AlertType.ERROR, Util.ERROR, Util.NO_PARAMS);
            return false;
        }

        if (foodRadioButton.isSelected()) {
            return foodInfoController.checkIfInputValuesValid();
        } else if (drinkRadioButton.isSelected()) {
            return drinkInfoController.checkIfInputValuesValid();
        } else {
            JavaFXUtil.showAlert(Alert.AlertType.ERROR, Util.ERROR, "You have not chosen the type of Item.");
            return false;
        }
    }
}
