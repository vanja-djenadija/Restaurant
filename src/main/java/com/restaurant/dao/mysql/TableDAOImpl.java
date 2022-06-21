package com.restaurant.dao.mysql;

import com.restaurant.dao.TableDAO;
import com.restaurant.util.ConnectionPool;
import com.restaurant.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableDAOImpl implements TableDAO {
    @Override
    public List<Integer> getTables() {
        List<Integer> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM sto";

        try {
            connection = ConnectionPool.getInstance().checkOut();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                list.add(resultSet.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(connection);
            DBUtil.close(preparedStatement, connection);
        }
        return list;
    }
}
