package com.restaurant.dao.mysql;

import com.restaurant.dao.MenuDAO;
import com.restaurant.models.Drink;
import com.restaurant.models.Food;
import com.restaurant.models.Item;
import com.restaurant.util.ConnectionPool;
import com.restaurant.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAOImpl implements MenuDAO {

    @Override
    public List<Item> getItems(String name) {
        List<Item> list = new ArrayList<>();
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet rs;

        String callStatement = "{call artikliTrenutnogMenija(?)}";

        try {
            connection = ConnectionPool.getInstance().checkOut();
            callableStatement = connection.prepareCall(callStatement);
            callableStatement.setString(1, name);
            rs = callableStatement.executeQuery();

            while (rs.next())
                list.add(new Item(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4)));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(connection);
            DBUtil.close(callableStatement);
        }
        return list;
    }

    @Override
    public boolean addItem(Item item) {
        boolean result = false;
        Connection connection = null;
        //connection.setAutoCommit(false);
        CallableStatement callableStatement = null;
        ResultSet rs = null;
        String callStatementItem = "{call dodajArtikal(?,?,?,?)}";

        // Adding to ARTIKAL, ARTIKAL_IMA_MENI
        try {
            connection = ConnectionPool.getInstance().checkOut();
            callableStatement = connection.prepareCall(callStatementItem);
            callableStatement.setString(1, item.getName());
            callableStatement.setDouble(2, item.getPrice());
            callableStatement.setString(3, item.getDescription());
            callableStatement.registerOutParameter(4, Types.INTEGER);
            callableStatement.executeUpdate();

            int artikalId = callableStatement.getInt(4);
            // Adding to JELO or PIĆE
            if (item instanceof Food) {
                Food food = (Food) item;
                String callStatementFood = "{call dodajJelo(?,?,?,?)}";
                callableStatement = connection.prepareCall(callStatementFood);
                callableStatement.setInt(1, artikalId);
                callableStatement.setString(2, food.getRecipe());
                callableStatement.setString(3, food.getPortionSize());
                callableStatement.setString(4, food.getFoodCategory());
                result = callableStatement.executeUpdate() == 1;
            } else if (item instanceof Drink) {
                Drink drink = (Drink) item;
                String callStatementFood = "{call dodajPiće(?,?,?,?)}";
                callableStatement = connection.prepareCall(callStatementFood);
                callableStatement.setInt(1, artikalId);
                callableStatement.setInt(2, drink.getQuantity());
                callableStatement.setString(3, drink.getManufacturerCategory());
                callableStatement.setString(4, drink.getDrinkCategory());
                result = callableStatement.executeUpdate() == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(connection);
            DBUtil.close(callableStatement);
        }
        //connection.commit();
        return result;
    }

    @Override
    public boolean deleteItem(Item item) {
        boolean result = false;
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet rs = null;
        String callStatementItem = "{call obrišiArtikal(?)}";

        try {
            connection = ConnectionPool.getInstance().checkOut();
            callableStatement = connection.prepareCall(callStatementItem);
            callableStatement.setInt(1, item.getId());
            result = callableStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(connection);
            DBUtil.close(callableStatement);
        }
        return result;
    }

    @Override
    public boolean updateItem(Item type) throws Exception {
        return false;
    }

    @Override
    public List<Item> filterItems(String filter) throws Exception {
        return null;
    }
}
