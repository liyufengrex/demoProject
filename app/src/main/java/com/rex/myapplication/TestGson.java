package com.rex.myapplication;

import com.rex.myapplication.utils.JsonConversionUtil;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @Author: 李宇峰 on 2019-01-04 2:39 PM.
 * @NO.: 146714
 * @Phone: 13756017116
 * @Email: liyufeng@syswin.com
 * @Leader：yupengfei <yupengfei@syswin.com>
 * @Charge: 李宇峰
 */
public class TestGson {

    public static void main(String[] args){
        Map<String,String> map = new HashMap<>();
        map.put("rex","http://a ");


        String jsonStr = JsonConversionUtil.toJson(map);
        System.out.println(jsonStr);
    }

}
