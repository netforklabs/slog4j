package com.sophon;

import com.sophon.component.anno.Alone;
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

    @Alone(value = "classpath:/system/ios_debug.log")
    private static final SophonLogger log1 = LoggerFactory.getLogger(Example.class,"log1");

    @Alone(value = "classpath:/system/android_debug.log")
    private static final SophonLogger log2 = LoggerFactory.getLogger(Example.class,"log2");

    public static void main(String[] args) throws ParamException {
        Logger.debug("logger debug");
        log1.debug("log1 debug");
        log2.debug("log2 debug");
    }

}
