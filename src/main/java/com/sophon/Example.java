package com.sophon;

import com.google.common.collect.Lists;
import com.sophon.component.anno.Alone;
import com.sophon.component.logger.LoggerFactory;
import com.sophon.component.security.Security;
import com.sophon.component.security.SecurityManager;
import com.sophon.exception.ParamException;
import com.sophon.logger.Logger;
import com.sophon.logger.SophonLogger;

/**
 * @Author tiansheng
 * @Date 2019/9/12 3:40
 * @Description TODO
 */
public class Example {

    @Alone(value = "/system/ios_debug.log")
    private static final SophonLogger log1 = LoggerFactory.getLogger(Example.class,"log1");

    @Alone(value = "/system/android_debug.log")
    private static final SophonLogger log2 = LoggerFactory.getLogger(Example.class,"log2");

    public static void main(String[] args) throws ParamException {
        System.out.println(log1.equals(log2));
        log1.info("logger1 debug");
        log2.info("logger2 debug");
    }

}
