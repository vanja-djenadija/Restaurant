package com.restaurant.dao;

import com.restaurant.models.Order;
import com.restaurant.models.OrderItem;

import java.util.List;

public interface OrderDAO {
    List<Order> getOrders() throws Exception;

    boolean addOrder(int tableId, List<OrderItem> orderedItems) throws Exception;

    boolean deleteOrder(int orderId) throws Exception;

    boolean createBill(int orderId) throws Exception;
}
