package com.example.demo.pool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.IntStream;

/**
 * @Description
 * @Author yangdong@0easy.com
 * @Date Created in 2020/4/24 17:58
 **/
public class TestPool {
    public static void main(String[] args) {
        DefaultPool poolFactory = PoolFactory.getPoolFactory();
        IntStream.range(0,51).forEach(
                i->{
                    PooledConnection pooledConnection = poolFactory.getPooledConnection();
                    ResultSet resultSet = pooledConnection.query("select * from user");
                    try {
                        if (resultSet.next()){
                            System.out.println(pooledConnection+"=====name:"+resultSet.getString("name"));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    pooledConnection.release();
                }
        );
    }
}
