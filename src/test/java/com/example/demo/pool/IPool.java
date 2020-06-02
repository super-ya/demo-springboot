package com.example.demo.pool;

/**
 * @Description
 * @Author yangdong@0easy.com
 * @Date Created in 2020/4/26 14:46
 **/
public interface IPool {
    PooledConnection getPooledConnection();

    void createConnection(int count);

    void releaseConnection(PooledConnection connection);
}
