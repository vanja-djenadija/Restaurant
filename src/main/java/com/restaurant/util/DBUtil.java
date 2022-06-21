package com.restaurant.util;

import com.restaurant.models.DrinkCategory;

import java.sql.*;

public class DBUtil {

    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    public static PreparedStatement prepareStatement(Connection c, String sql, boolean retGenKeys, Object... values) throws SQLException {
        PreparedStatement ps = c.prepareStatement(sql,
                retGenKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);

        for (int i = 0; i < values.length; i++)
            ps.setObject(i + 1, values[i]);

        return ps;
    }

    public static boolean preparedStatementById(String query, int id) throws SQLException {
        boolean result;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().checkOut();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            result = preparedStatement.executeUpdate() == 1;
        } finally {
            ConnectionPool.getInstance().checkIn(connection);
            DBUtil.close(preparedStatement);
        }
        return result;
    }

    public static String signIn(String username, String password) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        boolean loginSuccessful = false;
        int userId;

        String callStatement = "{call prijava(?, ?, ?, ?)}";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            cs = conn.prepareCall(callStatement);
            cs.setString(1, username);
            cs.setString(2, password);
            cs.registerOutParameter(3, Types.BOOLEAN);
            cs.registerOutParameter(4, Types.INTEGER);

            cs.executeUpdate();

            loginSuccessful = cs.getBoolean(3);
            userId = cs.getInt(4);

            return loginSuccessful + "#" + userId;

        } catch (SQLException e) {
            throw e;
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            close(cs);
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection c = connectionPool.checkOut();
        return c;
    }

    public static void close(Connection c) {
        if (c != null)
            connectionPool.checkIn(c);
    }

    public static void close(Statement s) {
        if (s != null)
            try {
                s.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public static void close(ResultSet rs) {
        if (rs != null)
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public static void close(ResultSet rs, Statement s, Connection c) {
        close(rs);
        close(s);
        close(c);
    }

    public static void close(Statement s, Connection c) {
        close(s);
        close(c);
    }
}
