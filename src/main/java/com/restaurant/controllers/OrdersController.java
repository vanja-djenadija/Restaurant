package com.restaurant.controllers;

import com.restaurant.dao.OrderDAO;
import com.restaurant.dao.mysql.OrderDAOImpl;
import com.restaurant.models.Order;
import com.restaurant.util.JavaFXUtil;
import com.restaurant.util.Util;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class OrdersController {

    private static final String FXML_PATH = "/fxml/CreateOrder.fxml";

    @FXML
    private TableView<Order> tableView;

    @FXML
    public TableColumn<Order, Integer> id;

    @FXML
    public TableColumn<Order, String> dateTime;

    @FXML
    public TableColumn<Order, Integer> tableId;

    @FXML
    public TableColumn<Order, String> status;

    @FXML
    public TableColumn<Order, Integer> billId;

    private OrderDAO orderDAO;

    public void initialize() {
        orderDAO = new OrderDAOImpl();

        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        dateTime.setCellValueFactory(new PropertyValueFactory<>("DateTime"));
        tableId.setCellValueFactory(new PropertyValueFactory<>("TableId"));
        status.setCellValueFactory(new PropertyValueFactory<>("Status"));
        billId.setCellValueFactory(new PropertyValueFactory<>("BillId"));
        search();
    }

    private void search() {
        List<Order> list;
        try {
            list = orderDAO.getOrders();
            tableView.getItems().clear();
            tableView.setItems(FXCollections.observableArrayList(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addOrder(ActionEvent actionEvent) throws IOException {
        JavaFXUtil.newInputWindow("Create new Order", FXML_PATH);
        search();
    }


    public void deleteOrder() {
        Order order = tableView.getSelectionModel().getSelectedItem();
        if (order == null) {
            JavaFXUtil.showAlert(Alert.AlertType.WARNING, Util.WARNING, Util.SELECT_BEFORE_DELETE);
            return;
        }
        try {
            JavaFXUtil.showInfoAlert(orderDAO.deleteOrder(order.getId()), Util.INFO, Util.DELETE_SUCCESS, Util.DELETE_FAILURE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JavaFXUtil.showAlert(Alert.AlertType.ERROR, Util.ERROR, Util.ERROR);
        }
        search();
    }


    public void makeBill() {
        try {
            Order order = tableView.getSelectionModel().getSelectedItem();
            if (order.getStatus().equals("Završena")) {
                JavaFXUtil.showAlert(Alert.AlertType.INFORMATION, Util.INFO, "It is not possible to create bill for completed order.");
                return;
            }
            JavaFXUtil.showInfoAlert(orderDAO.createBill(order.getId()), Util.INFO, "Successfully created the bill", "Unsuccessfully created the bill.");

        } catch (Exception ex) {
            ex.printStackTrace();
            JavaFXUtil.showAlert(Alert.AlertType.ERROR, Util.ERROR, Util.ERROR);
        }
        search();
    }

    public void searchOrder() {
    }

}
