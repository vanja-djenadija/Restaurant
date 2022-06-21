package com.restaurant.dao.mysql;

import com.restaurant.dao.DrinkDAO;
import com.restaurant.util.ConnectionPool;
import com.restaurant.util.DBUtil;

import java.sql.*;

public class DrinkDAOImpl implements DrinkDAO {

    @Override
    public boolean checkDrinkQuantity(int drinkId, int quantity) {
        boolean result = false;
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet rs = null;
        String callStatementItem = "{call provjeraStanjaPića(?,?,?)}";

        try {
            connection = ConnectionPool.getInstance().checkOut();
            callableStatement = connection.prepareCall(callStatementItem);
            callableStatement.setInt(1, drinkId);
            callableStatement.setInt(2, quantity);
            callableStatement.registerOutParameter(3, Types.BOOLEAN);

            result = callableStatement.executeUpdate() == 1;

            boolean canAddDrink = callableStatement.getBoolean(3);
            return canAddDrink;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(connection);
            DBUtil.close(callableStatement);
        }
        return result;
    }
}
