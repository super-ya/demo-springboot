package com.example.demo.pool;

/**
 * @Description
 * @Author yangdong@0easy.com
 * @Date Created in 2020/4/26 17:16
 **/
public class PoolFactory {
    private static class SinglePool{
        public static DefaultPool pool = new DefaultPool();
    }
    public static DefaultPool getPoolFactory(){
        return SinglePool.pool;
    }
}
