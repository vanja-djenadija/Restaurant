package com.restaurant.controllers;

import com.restaurant.dao.MenuDAO;
import com.restaurant.dao.mysql.DrinkDAOImpl;
import com.restaurant.dao.mysql.MenuDAOImpl;
import com.restaurant.dao.mysql.OrderDAOImpl;
import com.restaurant.dao.mysql.TableDAOImpl;
import com.restaurant.models.Item;
import com.restaurant.models.OrderItem;
import com.restaurant.util.JavaFXUtil;
import com.restaurant.util.Util;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderController {
    @FXML
    private TableView menuTableView;

    @FXML
    private TableView orderTableView;

    @FXML
    private TableColumn<Item, Integer> menuId;

    @FXML
    private TableColumn<Item, String> menuName;

    @FXML
    private TableColumn<Item, Double> menuPrice;

    @FXML
    private TableColumn<Item, Integer> orderItemId;

    @FXML
    private TableColumn<Item, String> orderItemName;

    @FXML
    private TableColumn<Item, Integer> orderItemQuantity;

    @FXML
    private TableColumn<Item, Double> orderItemPrice;

    @FXML
    private ComboBox<String> tableComboBox;

    private MenuDAO menuDAO;

    private ArrayList<OrderItem> orderedItems = new ArrayList<>();

    @FXML
    public void initialize() {
        menuDAO = new MenuDAOImpl();
        menuId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        menuName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        menuPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        orderItemId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        orderItemName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        orderItemQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        orderItemPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        search("%");
        setTables();
    }

    private void setTables() {
        if (tableComboBox.getItems().isEmpty()) {
            List<Integer> list = null;
            try {
                list = new TableDAOImpl().getTables();
                for (Integer i : list)
                    tableComboBox.getItems().add("Table " + i.intValue());

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        tableComboBox.getSelectionModel().select(0);
    }

    private void search(String name) {
        List<Item> list;
        try {
            list = menuDAO.getItems(name + "%");
            menuTableView.getItems().clear();
            menuTableView.setItems(FXCollections.observableArrayList(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateOrder() {
        orderTableView.getItems().clear();
        orderTableView.setItems(FXCollections.observableArrayList(orderedItems));
    }

    public void addItemToOrder() {
        Item item = (Item) menuTableView.getSelectionModel().getSelectedItem();
        if (item != null) {
            for (OrderItem orderItem : orderedItems) {
                int quantity = orderItem.getQuantity();
                int itemId = orderItem.getId();
                if (itemId == item.getId()) {
                    if (new DrinkDAOImpl().checkDrinkQuantity(item.getId(), quantity)) {
                        orderItem.incrementQuantity();
                        updateOrder(); // TODO: BIND model controller
                    }
                    return;
                }
            }
            orderedItems.add(new OrderItem(item));
            updateOrder();
        }
    }

    public void removeItemFromOrder() {
        OrderItem orderedItem = (OrderItem) orderTableView.getSelectionModel().getSelectedItem();
        if (orderedItem != null)
            for (OrderItem orderItem : orderedItems) {
                if (orderItem.getId() == orderedItem.getId()) {
                    if (orderItem.getQuantity() > 1)
                        orderItem.decrementQuantity();
                    else {
                        orderedItems.remove(orderedItem);
                    }
                    updateOrder();
                    return;
                }
            }
    }

    public void createOrder() {
        if (orderedItems.isEmpty()) {
            JavaFXUtil.showAlert(Alert.AlertType.INFORMATION, Util.INFO, "You did not choose order items");
            return;
        }
        String table = tableComboBox.getSelectionModel().getSelectedItem();
        int tableId = Integer.parseInt(table.split(" ")[1]);
        try {
            new OrderDAOImpl().addOrder(tableId, orderedItems);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) tableComboBox.getScene().getWindow();
        stage.close();
    }
}
