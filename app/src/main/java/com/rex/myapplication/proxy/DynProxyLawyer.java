package com.rex.myapplication.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description: 描述
 * @Author: liyufeng
 * @CreateDate: 2020-05-18 14:11
 */

public class DynProxyLawyer implements InvocationHandler {

    private Object target;//被代理的对象
    public DynProxyLawyer(Object obj){
        this.target = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("案件进展："+method.getName());
        Object result=method.invoke(target,args);
        return result;
    }

    public static void main(String[] args){
        ILawSuit proxy= (ILawSuit) ProxyFactory.getDynProxy(new CuiHuaNiu());
        proxy.submit("工资流水在此");
        proxy.defend();
    }
}
 class ProxyFactory {

    public static Object getDynProxy(Object target) {
        InvocationHandler handler = new DynProxyLawyer(target);
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
    }
}


