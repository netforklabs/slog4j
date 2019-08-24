package com.meranti.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author keyboard
 * @time 2019/6/29 0:52
 * @descripting 计算程序运行时间
 */
public class TimeCalc implements InvocationHandler {

    private TimeCalc() {
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.currentTimeMillis();
        method.invoke(proxy, args);
        long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + (endTime - startTime) + "ms");
        return null;
    }

    public static void invoke(Class<?> proxy, String name) {
        Method method = null;
        try {

            method = proxy.getMethod(name);
            new TimeCalc().invoke(proxy.newInstance(), method, null);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}
