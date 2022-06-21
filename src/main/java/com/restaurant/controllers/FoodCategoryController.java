package com.restaurant.controllers;

import com.restaurant.dao.GenericCategoryDAO;
import com.restaurant.dao.mysql.FoodCategoryDAOImpl;
import com.restaurant.models.FoodCategory;
import com.restaurant.util.JavaFXUtil;
import com.restaurant.util.Util;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class FoodCategoryController {

    private static final String FXML_PATH = "/fxml/AddFoodCategoryWindow.fxml";
    @FXML
    private TableView<FoodCategory> tableView;

    @FXML
    private TableColumn<FoodCategory, Integer> id;

    @FXML
    private TableColumn<FoodCategory, String> name;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField searchField;

    private GenericCategoryDAO foodCategoryDAO;

    @FXML
    public void initialize() {
        foodCategoryDAO = new FoodCategoryDAOImpl();
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        search("%");
    }

    private void search(String name) {
        List<FoodCategory> list;
        try {
            list = foodCategoryDAO.getCategory(name + "%");
            tableView.getItems().clear();
            tableView.setItems(FXCollections.observableArrayList(list));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addFoodCategory() throws IOException {
        JavaFXUtil.newInputWindow("Add new Food Category", FXML_PATH);
        search("%");
    }

    public void deleteFoodCategory() {
        try {
            FoodCategory type = tableView.getSelectionModel().getSelectedItem();
            if (type == null) {
                JavaFXUtil.showAlert(Alert.AlertType.WARNING, Util.WARNING, Util.SELECT_BEFORE_DELETE);
                return;
            }
            JavaFXUtil.showInfoAlert(foodCategoryDAO.deleteCategory(type), Util.INFO, Util.DELETE_SUCCESS, Util.DELETE_FAILURE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JavaFXUtil.showAlert(Alert.AlertType.ERROR, Util.ERROR, Util.ERROR);
        }
        search("%");
    }

    public void searchFoodCategory() {
        search(searchField.getText());
    }
}
