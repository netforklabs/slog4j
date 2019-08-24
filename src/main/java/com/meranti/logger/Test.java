package com.meranti.logger;

import com.sophon.framework.util.TimeCalc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author Mr.luo
 * @Date 2019/8/25 0:00
 * @Description TODO
 */
public class Test {

    private static final Logger log4j = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        // 244ms
        // TimeCalc.invoke(Test.class,"sophonLog");
        // 60ms
        TimeCalc.invoke(Test.class,"slf4j");
    }

    public void sophonLog(){
        for(int i=0; i<10000; i++){
            log.info("hello");
        }
    }

    public void slf4j(){
        for(int i=0; i<10000; i++){
            log4j.info("hello");
        }
    }

}
