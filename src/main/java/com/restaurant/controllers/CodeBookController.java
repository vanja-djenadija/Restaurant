package com.restaurant.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Objects;


public class CodeBookController  {

    @FXML
    private BorderPane borderPane;


    public void foodCategory() throws IOException {
        Parent loader = FXMLLoader.load(Objects.requireNonNull(EmployeeController.class.getResource("/fxml/FoodCategory.fxml")));
        borderPane.setCenter(loader);
    }

    public void drinkCategory() throws IOException {
        Parent loader = FXMLLoader.load(Objects.requireNonNull(EmployeeController.class.getResource("/fxml/DrinkCategory.fxml")));
        borderPane.setCenter(loader);
    }

    public void manufacturer() throws IOException {
        Parent loader = FXMLLoader.load(Objects.requireNonNull(EmployeeController.class.getResource("/fxml/ManufacturerCategory.fxml")));
        borderPane.setCenter(loader);
    }
}
