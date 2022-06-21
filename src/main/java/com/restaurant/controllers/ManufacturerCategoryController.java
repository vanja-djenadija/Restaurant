package com.restaurant.controllers;

import com.restaurant.dao.GenericCategoryDAO;
import com.restaurant.dao.mysql.ManufacturerCategoryDAOImpl;
import com.restaurant.models.ManufacturerCategory;
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

public class ManufacturerCategoryController {
    private static final String FXML_PATH = "/fxml/AddManufacturerCategoryWindow.fxml";
    @FXML
    private TableView<ManufacturerCategory> tableView;

    @FXML
    private TableColumn<ManufacturerCategory, Integer> id;

    @FXML
    private TableColumn<ManufacturerCategory, String> name;

    @FXML
    private TextField searchField;

    private GenericCategoryDAO manufacturerCategoryDAO;

    @FXML
    public void initialize() {
        manufacturerCategoryDAO = new ManufacturerCategoryDAOImpl();
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        search("%");
    }

    private void search(String name) {
        List<ManufacturerCategory> list;
        try {
            list = manufacturerCategoryDAO.getCategory(name + "%");
            tableView.getItems().clear();
            tableView.setItems(FXCollections.observableArrayList(list));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addManufacturerCategory() throws IOException {
        JavaFXUtil.newInputWindow("Add new Food Category", FXML_PATH);
        search("%");
    }

    public void deleteManufacturerCategory() {
        try {
            ManufacturerCategory type = tableView.getSelectionModel().getSelectedItem();
            if (type == null) {
                JavaFXUtil.showAlert(Alert.AlertType.WARNING, Util.WARNING, Util.SELECT_BEFORE_DELETE);
                return;
            }
            JavaFXUtil.showInfoAlert(manufacturerCategoryDAO.deleteCategory(type), Util.INFO, Util.DELETE_SUCCESS, Util.DELETE_FAILURE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JavaFXUtil.showAlert(Alert.AlertType.ERROR, Util.ERROR, Util.ERROR);
        }
        search("%");
    }

    public void searchManufacturerCategory() {
        search(searchField.getText());
    }
}
