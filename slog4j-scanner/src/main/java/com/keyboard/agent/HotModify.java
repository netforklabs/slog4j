package com.keyboard.agent;

import com.keyboard.register.InstrumentImpl;
import javassist.ClassPool;

import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;

/**
 * 热加载，需要被调用的模块调用该参数。并且传入已编译好的字节码
 *
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/30 2:01
 * @since 1.8
 */
public class HotModify {

    /**
     * <p>
     *     将已被加载到JVM中的类进行重新更改，可以理解为热部署。
     * </p>
     * @param classpath 类路径，用于Class.forName()加载。
     * @param byteCode 字节码数组
     */
    public static void redefine(String classpath, byte[] byteCode) {
        try {
            ClassDefinition redef = new ClassDefinition(Class.forName(classpath), byteCode);
            // 重新加载类
            InstrumentImpl.redefineClasses(redef);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
