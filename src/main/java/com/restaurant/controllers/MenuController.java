package com.restaurant.controllers;


import com.restaurant.dao.MenuDAO;
import com.restaurant.dao.mysql.MenuDAOImpl;
import com.restaurant.models.Item;
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

public class MenuController {

    private static final String FXML_PATH = "/fxml/AddItemWindow.fxml";

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Item> tableView;

    @FXML
    public TableColumn<Item, Integer> id;

    @FXML
    public TableColumn<Item, String> name;

    @FXML
    public TableColumn<Item, Double> price;

    private MenuDAO menuDAO;

    @FXML
    public void initialize() {
        menuDAO = new MenuDAOImpl();
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        search("%");
    }

    private void search(String name) {
        List<Item> list;
        try {
            list = menuDAO.getItems(name + "%");
            tableView.getItems().clear();
            tableView.setItems(FXCollections.observableArrayList(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchMenuItem() {
        search(searchField.getText());
    }

    public void addMenuItem() throws IOException {
        JavaFXUtil.newInputWindow("Add new Item", FXML_PATH);
        search("%");
    }

    public void deleteMenuItem() {
        try {
            Item item = tableView.getSelectionModel().getSelectedItem();
            if (item == null) {
                JavaFXUtil.showAlert(Alert.AlertType.WARNING, Util.WARNING, Util.SELECT_BEFORE_DELETE);
                return;
            }
            JavaFXUtil.showInfoAlert(menuDAO.deleteItem(item), Util.INFO, Util.DELETE_SUCCESS, Util.DELETE_FAILURE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JavaFXUtil.showAlert(Alert.AlertType.ERROR, Util.ERROR, Util.ERROR);
        }
        search("%");
    }

    public void editMenuItem() {
    }

}
