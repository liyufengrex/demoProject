package com.rex.myapplication;

/**
 * @Description: 描述
 * @Author: liyufeng
 * @CreateDate: 2020-05-15 16:20
 */

public class SingleInstanceTest {

    private SingleInstanceTest(){}

    public static SingleInstanceTest getInstance(){
        return Inner.test;
    }

    public static class Inner{
        private static SingleInstanceTest test = new SingleInstanceTest();
    }

}
