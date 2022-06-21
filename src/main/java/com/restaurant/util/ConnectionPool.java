package com.restaurant.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

// Singleton pattern
public class ConnectionPool {

    private static final String BUNDLE_NAME = "resources/ConnectionPool.properties";

    private String jdbcURL;
    private String username;
    private String password;
    private int preconnectCount;
    private int maxIdleConnections;
    private int maxConnections;

    private int connectCount;
    private List<Connection> usedConnections;
    private List<Connection> freeConnections;

    private static ConnectionPool instance;

    public static ConnectionPool getInstance() {
        if (instance == null)
            instance = new ConnectionPool();
        return instance;
    }

    private ConnectionPool() {
        readConfiguration();
        try {
            freeConnections = new ArrayList<>();
            usedConnections = new ArrayList<>();

            for (int i = 0; i < preconnectCount; i++) {
                Connection conn = DriverManager.getConnection(jdbcURL,
                        username, password);
                freeConnections.add(conn);
            }
            connectCount = preconnectCount;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readConfiguration() {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(BUNDLE_NAME)) {

            prop.load(input);
            jdbcURL = prop.getProperty("jdbcURL");
            username = prop.getProperty("username");
            password = prop.getProperty("password");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        preconnectCount = 0;
        maxIdleConnections = 10;
        maxConnections = 10;
        try {
            preconnectCount = Integer.parseInt(prop.getProperty("preconnectCount"));
            maxIdleConnections = Integer.parseInt(prop.getProperty("maxIdleConnections"));
            maxConnections = Integer.parseInt(prop.getProperty("maxConnections"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized Connection checkOut() throws SQLException {
        Connection conn = null;
        if (freeConnections.size() > 0) {
            conn = freeConnections.remove(0);
            usedConnections.add(conn);
        } else {
            if (connectCount < maxConnections) {
                conn = DriverManager.getConnection(jdbcURL, username, password);
                usedConnections.add(conn);
                connectCount++;
            } else {
                try {
                    wait();
                    conn = freeConnections.remove(0);
                    usedConnections.add(conn);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return conn;
    }

    public synchronized void checkIn(Connection conn) {
        if (conn == null)
            return;
        if (usedConnections.remove(conn)) {
            freeConnections.add(conn);
            while (freeConnections.size() > maxIdleConnections) {
                int lastOne = freeConnections.size() - 1;
                Connection c = freeConnections.remove(lastOne);
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            notify();
        }
    }
}
