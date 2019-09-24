package com.sophon.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 计算程序运行时间
 * @author keyboard
 * @date 2019/6/29 0:52
 * @version 1.0
 * @since 1.8
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

    /**
     * 调用方法
     * @param proxy 传入一个类的类对象
     * @param name 传入当前proxy中的某个方法名
     */
    public static void invoke(Class<?> proxy, String name) {
        try {
            Method method = proxy.getMethod(name);
            new TimeCalc().invoke(proxy.newInstance(), method, null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}
