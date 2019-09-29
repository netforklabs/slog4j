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
     * 类加载拦截器，在类初始加载时会进行拦截。
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
        String name = className.trim().replaceAll("/", ".");
        if (ListenerMethodManager.get(name) != null) {
            try {
                // 从ClassPool获得CtClass对象
                final ClassPool pool = ClassPool.getDefault();
                final CtClass cbtCtClass = pool.get("future.Test");
                CtMethod method = cbtCtClass.getDeclaredMethod("test");
                method.insertBefore("com.sophon.logger.source.Logger.info(\"--开始打印\");");
                method.insertAfter("com.sophon.logger.source.Logger.info(\"--打印完成\");");
                // 返回字节码，并且detachCtClass对象
                byte[] byteCode = cbtCtClass.toBytecode();
                //detach的意思是将内存中曾经被javassist加载过的Date对象移除，如果下次有需要在内存中找不到会重新走javassist加载
                cbtCtClass.detach();
                return byteCode;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return classfileBuffer;
    }

}
