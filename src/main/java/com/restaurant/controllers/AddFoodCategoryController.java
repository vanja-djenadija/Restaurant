package com.restaurant.controllers;

import com.restaurant.dao.mysql.FoodCategoryDAOImpl;
import com.restaurant.models.FoodCategory;
import com.restaurant.util.JavaFXUtil;
import com.restaurant.util.Util;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddFoodCategoryController {

    @FXML
    private Button saveButton;

    @FXML
    private TextField nameTextField;


    public void addFoodCategory() {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        try {
            if (nameTextField.getText().equals("")) {
                JavaFXUtil.showAlert(Alert.AlertType.ERROR, Util.ERROR, Util.NO_PARAMS);
                return;
            }
            boolean success = new FoodCategoryDAOImpl().addCategory(new FoodCategory(0, nameTextField.getText()));
            JavaFXUtil.showInfoAlert(success, Util.INFO, Util.ADD_SUCCESS, Util.ADD_FAILURE);
            stage.close();
        } catch (Exception ex) {
            JavaFXUtil.showAlert(Alert.AlertType.ERROR, Util.ERROR, Util.ERROR);
        }
    }
}

