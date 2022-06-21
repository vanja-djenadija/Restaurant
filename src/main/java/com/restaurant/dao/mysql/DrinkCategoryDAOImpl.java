package com.restaurant.dao.mysql;

import com.restaurant.dao.GenericCategoryDAO;
import com.restaurant.models.DrinkCategory;
import com.restaurant.util.ConnectionPool;
import com.restaurant.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrinkCategoryDAOImpl implements GenericCategoryDAO<DrinkCategory> {

    @Override
    public List<DrinkCategory> getCategory(String name) throws Exception {
        List<DrinkCategory> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM vrsta_pića";

        if (name != null)
            query += " WHERE Naziv like ?";

        try {
            connection = ConnectionPool.getInstance().checkOut();
            preparedStatement = connection.prepareStatement(query);

            if (name != null)
                preparedStatement.setString(1, name);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                list.add(new DrinkCategory(resultSet.getInt(1), resultSet.getString(2)));
        } finally {
            ConnectionPool.getInstance().checkIn(connection);
            DBUtil.close(preparedStatement, connection);
        }
        return list;
    }

    @Override
    public boolean addCategory(DrinkCategory type) throws Exception {
        boolean result = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String query = "INSERT INTO vrsta_pića VALUES " + "(?, ?) ";
        try {
            connection = ConnectionPool.getInstance().checkOut();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, type.getId());
            preparedStatement.setString(2, type.getName());

            result = preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(connection);
            DBUtil.close(preparedStatement);
        }
        return result;
    }

    @Override
    public boolean deleteCategory(DrinkCategory type) throws Exception {
        String query = "DELETE FROM vrsta_pića " + "WHERE IdVrstaPića=? ";
        return DBUtil.preparedStatementById(query, type.getId());
    }


}
