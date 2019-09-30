package com.sophon.component.hot;

import java.lang.reflect.Method;

/**
 *
 * @author 2BKeyboard
 * @version 1.0.0
 * @date 2019/9/25 23:59
 * @since 1.8
 */
public interface SophonListener {

    /**
     * 当方法被调用时
     * @param target 当前class
     * @param method 被调用的方法
     * @param args 被调用方法的参数
     */
    void before(Class<?> target, String method, Object[] args);

    /**
     * 当方法结束时
     * @param target 当前class
     * @param method 被调用的方法
     * @param args 被调用方法的参数
     */
    void after(Class<?> target, String method, Object[] args);

    /**
     * 当发生异常时会调用这个方法，如果不做任何处理则不会影响什么。
     * @param target 发生异常的类
     * @param method 发生异常的方法
     * @param e 异常类型
     */
    void error(Class<?> target,Method method,Throwable e);

}
