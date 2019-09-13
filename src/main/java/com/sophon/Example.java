package com.sophon;

import com.sophon.component.exception.UncheckedExceptionHandler;
import com.sophon.logger.SystemLogger;

/**
 * @Author tiansheng
 * @Date 2019/9/12 3:40
 * @Description TODO
 */
public class Example {

    // private static final Logger log = LoggerFactory.getLogger(Example.class);

    public static void main(String[] args) {
        SystemLogger.info("system start...");
        String s = null;
        s.toLowerCase();
        SystemLogger.info("system exit...");
    }

}
