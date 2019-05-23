package com.rex.myapplication.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author: 李宇峰 on 2018-11-05 4:18 PM.
 * @NO.: 146714
 * @Phone: 13756017116
 * @Email: liyufeng@syswin.com
 * @Leader：yupengfei <yupengfei@syswin.com>
 * @Charge: 李宇峰
 */
public enum EnumDemo {
    ONE(1), TWO(2);

    private final static List<Integer> vals;

    static {
        System.out.println("fetch instance from static");
        vals = new ArrayList<>();
        EnumDemo[] values = EnumDemo.values();
        for (EnumDemo value : values) {
            vals.add(value.val);
        }
    }

    private int val;

    EnumDemo(int val) {
        this.val = val;
        System.out.println("create instance:" + val);
    }
}
