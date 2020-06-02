package com.example.demo.pool;

import static com.example.demo.pool.DBConfig.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;
import java.util.stream.IntStream;

/**
 * @Description
 * @Author yangdong@0easy.com
 * @Date Created in 2020/4/26 14:48
 **/
public class DefaultPool implements IPool {

    private Vector<PooledConnection> connections = new Vector<>();

    DefaultPool(){
        init();
    }

    private void init(){
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage()+":init failed!");
        }
        createConnection(initCount);
    }

    @Override
    public PooledConnection getPooledConnection() {
        PooledConnection pooledConnection = null;
        for (PooledConnection connection:connections){
            if (!connection.isBusy()){
                connection.setBusy(true);
                pooledConnection = connection;
                break;
            }
        }
        if (pooledConnection==null){
            createConnection(growStep);
            return getPooledConnection();
        }else {
            return pooledConnection;
        }
    }

    @Override
    public void createConnection(int count) {
        Connection connection;
        try {
            if ((connections.size() + count) <= maxCount) {
                connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
                IntStream.range(0, count).forEach(i -> {
                    connections.add(new PooledConnection(connection, false));
                });
            } else {
                throw new RuntimeException("连接数已达到最大值：" + connections.size());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void releaseConnection(PooledConnection connection) {
        connection.release();
    }
}
