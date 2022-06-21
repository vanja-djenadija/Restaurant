package com.restaurant.dao.mysql;

import com.restaurant.controllers.SignInController;
import com.restaurant.dao.OrderDAO;
import com.restaurant.models.Order;
import com.restaurant.models.OrderItem;
import com.restaurant.models.Status;
import com.restaurant.util.ConnectionPool;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.restaurant.util.DBUtil.close;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public List<Order> getOrders() throws Exception {
        List<Order> list = new ArrayList<>();
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet rs;

        String callStatement = "{call narudžbeZaposlenog(?)}";

        try {
            connection = ConnectionPool.getInstance().checkOut();
            callableStatement = connection.prepareCall(callStatement);
            int userId = SignInController.getUserId(); // !
            callableStatement.setInt(1, userId);
            rs = callableStatement.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt(1);
                LocalDateTime dateTime = rs.getTimestamp(2).toLocalDateTime();
                int tableId = rs.getInt(3);
                int billId = rs.getInt(5);
                int employeeId = rs.getInt(6);
                int personId = rs.getInt(7);

                int statusId = rs.getInt(8);
                String statusName = rs.getString(9);
                Order order = new Order(orderId, dateTime, tableId, new Status(statusId, statusName), billId, employeeId, personId);
                list.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(connection);
            close(callableStatement);
        }
        return list;
    }

    @Override
    public boolean addOrder(int tableId, List<OrderItem> orderItems) throws Exception {
        Connection connection = null;
        CallableStatement callableStatement;
        boolean result = false;
        String callStatement = "{call dodajNarudžbu(?,?,?)}";
        int orderId = 0;
        try {
            connection = ConnectionPool.getInstance().checkOut();
            callableStatement = connection.prepareCall(callStatement);

            callableStatement.setInt(1, tableId);
            callableStatement.setInt(2, SignInController.getUserId());
            callableStatement.registerOutParameter(3, Types.INTEGER);
            callableStatement.executeUpdate();
            orderId = callableStatement.getInt(3);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PreparedStatement preparedStatement = null;

        String query = "INSERT INTO narudžba_ima_artikal VALUES " + "(?, ?, ?) ";
        for (OrderItem orderItem : orderItems) {
            try {
                connection = ConnectionPool.getInstance().checkOut();
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, orderId);
                preparedStatement.setInt(2, orderItem.getId());
                preparedStatement.setInt(3, orderItem.getQuantity());

                result = preparedStatement.executeUpdate() == 1;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConnectionPool.getInstance().checkIn(connection);
                close(preparedStatement);
            }
        }

        return result;
    }

    @Override
    public boolean deleteOrder(int orderId) throws Exception {
        boolean result = false;
        Connection connection = null;
        CallableStatement callableStatement = null;

        String callStatement = "{call obrišiNarudžbu(?)}";

        try {
            connection = ConnectionPool.getInstance().checkOut();
            callableStatement = connection.prepareCall(callStatement);
            callableStatement.setInt(1, orderId);
            result = callableStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(connection);
            close(callableStatement);
        }
        return result;
    }

    @Override
    public boolean createBill(int orderId) throws Exception {
        boolean result = false;
        Connection connection = null;
        CallableStatement callableStatement = null;

        String callStatement = "{call kreirajRačun(?)}";

        try {
            connection = ConnectionPool.getInstance().checkOut();
            callableStatement = connection.prepareCall(callStatement);
            callableStatement.setInt(1, orderId);
            result = callableStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(connection);
            close(callableStatement);
        }
        return result;
    }
}
