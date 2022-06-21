package com.restaurant.controllers;

import com.restaurant.dao.mysql.FoodCategoryDAOImpl;
import com.restaurant.models.FoodCategory;
import com.restaurant.util.JavaFXUtil;
import com.restaurant.util.Util;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.stream.Collectors;

public class FoodInfoController {
    @FXML
    private TextField recipeTextField;

    @FXML
    private ComboBox portionComboBox;

    @FXML
    private ComboBox foodTypeComboBox;

    public void setFoodCategories() {
        if (foodTypeComboBox.getItems().isEmpty()) {
            List<FoodCategory> list = null;
            List<String> categories = null;
            try {
                list = new FoodCategoryDAOImpl().getCategory("%");
                categories = list.stream().map(FoodCategory::getName).collect(Collectors.toList());
                foodTypeComboBox.getItems().addAll(categories);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void setPortionSizeCategories() {
        portionComboBox.getItems().addAll("Mala", "Srednja", "Velika", "Standardna");
        portionComboBox.getSelectionModel().select(3);
    }

    public boolean checkIfInputValuesValid() {
        if (foodTypeComboBox.getSelectionModel().isEmpty()) {
            JavaFXUtil.showAlert(Alert.AlertType.ERROR, Util.ERROR, "You have not chosen the type of food.");
            return false;
        }
        return true;
    }

    public String getRecipe() {
        return recipeTextField.getText();
    }

    public String getPortion() {
        return portionComboBox.getSelectionModel().getSelectedItem().toString();
    }

    public String getFoodCategory() {
        return foodTypeComboBox.getSelectionModel().getSelectedItem().toString();
    }
}
