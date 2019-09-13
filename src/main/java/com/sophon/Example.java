package com.sophon;

import com.sophon.logger.Logger;

import java.util.ArrayList;

/**
 * @Author tiansheng
 * @Date 2019/9/12 3:40
 * @Description TODO
 */
public class Example {

    // private static final Logger log = LoggerFactory.getLogger(Example.class);



    public static void main(String[] args) {

        for(int i=0; i<2000; i++){
            Logger.info("hello slog4j info");
            Logger.debug("hello slog4j debug");
            Logger.error("hello slog4j error");
            Logger.warn("hello slog4j warn");
        }

    }

    public static void iosPush(){

    }

    public static void androidPush(){

    }

}
