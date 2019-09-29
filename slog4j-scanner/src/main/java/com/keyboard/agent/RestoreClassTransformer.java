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
        System.out.println("premain load Class:" + className);
        return classfileBuffer;
    }

}
