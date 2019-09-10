package com.sophon;

import com.sophon.component.anno.Alone;
import com.sophon.logger.Level;
import com.sophon.logger.Logger;
import com.sophon.util.TimeCalc;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

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
        for(int i=0; i<10000; i++){
            Logger.debug("count:{}",i);
        }
    }

}
