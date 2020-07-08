package com.rex.myapplication.proxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description: 描述
 * @Author: liyufeng
 * @CreateDate: 2020-05-18 15:02
 *
 * https://www.jianshu.com/p/5c3f4b1c536b
 *
 * https://www.jianshu.com/p/097e574e36b0
 */

public class TestProxy {

    interface Subject{
        public CallObject getCallObject(String args);
    }

    static class CallObject{
        private String args;

        public CallObject(String args) {
            this.args = args;
        }

        public void call() {
            System.out.println("simulate"+"  "+"call args=" + args);
        }
    }

    public static void main(String[] args){
        Class mClass = Subject.class;
        Subject proxySubject = (Subject) Proxy.newProxyInstance(mClass.getClassLoader(),
                new Class[]{mClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        String modifiedArgs = (String) args[0] + " after modified";
                        System.out.println("simulate"+"  "+"invoke");
                        return new CallObject(modifiedArgs);
                    }
                });
        System.out.println("simulate"+"  "+"createProxy finished");
        CallObject callObject = proxySubject.getCallObject("args");
        System.out.println("simulate"+"  "+"getCallObject finished");
        callObject.call();
    }

}
