package com.restaurant.controllers;

import com.restaurant.dao.mysql.DrinkCategoryDAOImpl;
import com.restaurant.dao.mysql.ManufacturerCategoryDAOImpl;
import com.restaurant.models.DrinkCategory;
import com.restaurant.models.ManufacturerCategory;
import com.restaurant.util.JavaFXUtil;
import com.restaurant.util.Util;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.stream.Collectors;

public class DrinkInfoController {
    @FXML
    private ComboBox manufacturerComboBox;

    @FXML
    private ComboBox drinkTypeComboBox;

    @FXML
    private TextField quantityTextField;

    public void setDrinkCategories() {
        if (drinkTypeComboBox.getItems().isEmpty()) {
            List<DrinkCategory> list;
            List<String> categories;
            try {
                list = new DrinkCategoryDAOImpl().getCategory("%");
                categories = list.stream().map(DrinkCategory::getName).collect(Collectors.toList());
                drinkTypeComboBox.getItems().addAll(categories);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void setManufacturerCategories() {
        if (manufacturerComboBox.getItems().isEmpty()) {
            List<ManufacturerCategory> list;
            List<String> categories;
            try {
                list = new ManufacturerCategoryDAOImpl().getCategory("%");
                categories = list.stream().map(ManufacturerCategory::getName).collect(Collectors.toList());
                manufacturerComboBox.getItems().addAll(categories);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean checkIfInputValuesValid() {
        boolean result = true;
        String numberRegex = "\\d+"; // number
        String quantity = quantityTextField.getText();
        if (manufacturerComboBox.getSelectionModel().isEmpty() || drinkTypeComboBox.getSelectionModel().isEmpty() || quantity.equals("")) {
            JavaFXUtil.showAlert(Alert.AlertType.ERROR, Util.ERROR, Util.NO_PARAMS);
            result = false;
        }
        if (!quantity.matches(numberRegex)) {
            JavaFXUtil.showAlert(Alert.AlertType.ERROR, Util.ERROR, "Invalid format of Quantity entered.");
            result = false;
        }
        return result;
    }

    public int getQuantity() {
        return Integer.parseInt(quantityTextField.getText());
    }

    public String getDrinkCategory() {
        return drinkTypeComboBox.getSelectionModel().getSelectedItem().toString();
    }

    public String getManufacturerCategory() {
        return manufacturerComboBox.getSelectionModel().getSelectedItem().toString();
    }

}
