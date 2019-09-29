package com.keyboard.agent;

import com.keyboard.register.ListenerMethodManager;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/29 14:19
 * @since 1.8
 */
public class RestoreClassTransformer implements ClassFileTransformer {

    /**
     * 类加载拦截器，类在初始加载时会进行拦截。
     *
     * @param loader              类加载器
     * @param className           类路径，例如：com.keyboard.Example
     * @param classBeingRedefined 不懂
     * @param protectionDomain    不懂
     * @param classfileBuffer     class字节码
     * @return class字节码，因为在未加载的时候你可以对字节码进行更新。
     * @throws IllegalClassFormatException
     */
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        // 添加一个已被加载过的类
        ClassINFO.setLoaderClass(className.trim().replaceAll("/","."));
        return classfileBuffer;
    }

}
