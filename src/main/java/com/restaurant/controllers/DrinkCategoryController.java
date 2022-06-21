package com.restaurant.controllers;

import com.restaurant.dao.GenericCategoryDAO;
import com.restaurant.dao.mysql.DrinkCategoryDAOImpl;
import com.restaurant.models.DrinkCategory;
import com.restaurant.util.JavaFXUtil;
import com.restaurant.util.Util;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class DrinkCategoryController {

    private static final String FXML_PATH = "/fxml/AddDrinkCategoryWindow.fxml";

    @FXML
    private TableView<DrinkCategory> tableView;

    @FXML
    private TableColumn<DrinkCategory, Integer> id;

    @FXML
    private TableColumn<DrinkCategory, String> name;

    @FXML
    private TextField searchField;

    private GenericCategoryDAO categoryDAO;

    @FXML
    public void initialize() {
        categoryDAO = new DrinkCategoryDAOImpl();
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        search("%");
    }

    private void search(String name) {
        List<DrinkCategory> list;
        try {
            list = categoryDAO.getCategory(name + "%");
            tableView.getItems().clear();
            tableView.setItems(FXCollections.observableArrayList(list));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addDrinkCategory() throws IOException {
        JavaFXUtil.newInputWindow("Add new Drink Category", FXML_PATH);
        search("%");
    }

    public void deleteDrinkCategory() {
        DrinkCategory type = tableView.getSelectionModel().getSelectedItem();
        if (type == null) {
            JavaFXUtil.showAlert(Alert.AlertType.WARNING, Util.WARNING, Util.SELECT_BEFORE_DELETE);
            return;
        }
        try {
            JavaFXUtil.showInfoAlert(categoryDAO.deleteCategory(type), Util.INFO, Util.DELETE_SUCCESS, Util.DELETE_FAILURE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JavaFXUtil.showAlert(Alert.AlertType.ERROR, Util.ERROR, Util.ERROR);
        }
        search("%");
    }

    public void searchDrinkCategory() {
        search(searchField.getText());
    }
}
