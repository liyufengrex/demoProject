package com.rex.myapplication.proxy;

/**
 * @Description: 描述
 * @Author: liyufeng
 * @CreateDate: 2020-05-18 14:16
 */

public class CuiHuaNiu implements ILawSuit {
    @Override
    public void submit(String proof) {
        System.out.println(String.format("老板欠薪跑路，证据如下：%s",proof));
    }
    @Override
    public void defend() {
        System.out.println(String.format("铁证如山，%s还牛翠花血汗钱","马旭"));
    }
}