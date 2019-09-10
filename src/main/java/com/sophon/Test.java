package com.sophon;

import com.sophon.anno.Alone;
import com.sophon.logger.log;
import com.sophon.util.TimeCalc;

import java.io.*;

/**
 * @Author tiansheng
 * @Date 2019/8/25 1:32
 * @Description TODO
 */
@Alone("test.log")
public class Test {

    public static void main(String[] args) throws IOException {
        TimeCalc.invoke(Test.class, "test");
    }

    public void test() throws Exception {
        log.debug("a:{}",1);
    }

}
