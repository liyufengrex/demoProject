package com.rex.myapplication;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @Description: TODO
 * @Author: 李宇峰 on 2018-08-15 下午5:11.
 * @NO.: 146714
 * @Phone: 13756017116
 * @Email: liyufeng@syswin.com
 * @Leader：yupengfei <yupengfei@syswin.com>
 * @Charge: 李宇峰
 */
public class TestHash {

    public static void main(String[] args){
        new TestHash().test123();
    }

    public void test123(){
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put(null, 0);
        map.put("java", 1);
        map.put("c++", 2);
        map.put("python", 3);
        map.put("php", 4);
        map.put("nodejs", 5);
        for(Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("php".hashCode() == "c++".hashCode());
    }

}
