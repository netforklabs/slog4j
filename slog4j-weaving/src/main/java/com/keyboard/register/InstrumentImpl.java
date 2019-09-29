package com.keyboard.register;

import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * 二进制仪表
 *
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/30 2:19
 * @since 1.8
 */
public class InstrumentImpl {

    /**
     * Instrumentation实例，从agentmain方法参数中过去
     */
    private static Instrumentation inst;

    /**
     * 设置实例
     * @param instCopy Instrumentation
     */
    public static void setInstance(final Instrumentation instCopy) {
        inst = instCopy;
    }

    /**
     * 获取实例
     * @return Instrumentation
     */
    public static Instrumentation getInstance(){
        return inst;
    }

    /**
     * 重新对类进行加载
     * @param redefineClass 重定义后的类
     */
    public static void redefineClasses(ClassDefinition redefineClass){
        try {
            inst.redefineClasses(redefineClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (UnmodifiableClassException e) {
            e.printStackTrace();
        }
    }

}
