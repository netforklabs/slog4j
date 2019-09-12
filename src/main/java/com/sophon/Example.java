package com.sophon;

import com.sophon.component.anno.Alone;
import com.sophon.component.logger.LoggerFactory;
import com.sophon.component.security.Security;
import com.sophon.component.security.SecurityManager;
import com.sophon.exception.ParamException;
import com.sophon.logger.SophonLogger;

/**
 * @Author tiansheng
 * @Date 2019/9/12 3:40
 * @Description TODO
 */
public class Example {

    @Alone(value = "/system/ios_debug.log")
    private static final SophonLogger iosLogger = LoggerFactory.getLogger(Example.class);

    @Alone(value = "/system/android_debug.log")
    private static final SophonLogger anroidLogger = LoggerFactory.getLogger(Example.class);

    public static void main(String[] args) throws ParamException {
        Security security = SecurityManager.getSecurity();
        security.annoCheck(Example.class,Alone.class);
    }

}
