package com.keyboard.agent;

import javassist.*;
import lombok.Setter;

import javax.tools.Tool;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if ("future/Test".equals(className)) {
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
