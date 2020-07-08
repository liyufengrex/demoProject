package com.rex.myapplication;

/**
 * @Description: 描述
 * @Author: liyufeng
 * @CreateDate: 2020-05-19 15:05
 */

public class TestFitbo {

    public static void main(String[] args) {
        System.out.println(fitNum(4));
    }

    public static int fitNum(int index) {
        if (index == 1 || index == 2) {
            return 1;
        }
        return fitNum(index - 1) + fitNum(index - 2);
    }

}
