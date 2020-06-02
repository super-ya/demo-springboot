package com.example.demo.pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Description
 * @Author yangdong@0easy.com
 * @Date Created in 2020/4/26 14:38
 **/
public class PooledConnection {
    private Connection connection;
    private boolean isBusy;

    public PooledConnection(Connection connection, boolean isBusy) {
        this.connection = connection;
        this.isBusy = isBusy;
    }

    public void release() {
        this.isBusy = false;
    }

    public ResultSet query(String sql) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean isBusy){
        this.isBusy = isBusy;
    }
}
