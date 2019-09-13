package com.sophon;

import com.sophon.component.anno.Separation;
import com.sophon.component.logger.LoggerFactory;
import com.sophon.exception.ParamException;
import com.sophon.logger.Logger;
import com.sophon.logger.SophonLogger;

/**
 * @Author tiansheng
 * @Date 2019/9/12 3:40
 * @Description TODO
 */
public class Example {

    @Separation(value = "classpath:/system/ios_debug.log")
    private static SophonLogger log1;

    @Separation(value = "classpath:/system/android_debug.log")
    private static SophonLogger log2;

    @Separation(value = "classpath:/system/separation_debug.log")
    private static SophonLogger log3;

    static {
        Logger.debug("Logger Factory injection...");
        LoggerFactory.injection();
    }

    public static void main(String[] args) throws ParamException {
        for (int i = 0; i < 100; i++) {
            Logger.debug("Logger debug");
            log1.debug("logger1 debug");
            log2.debug("logger2 debug");
            log3.debug("separation debug");
        }
    }

}
