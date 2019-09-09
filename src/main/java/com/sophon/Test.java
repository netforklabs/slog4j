package com.sophon;

import com.sophon.io.SophonBufferedWriter;
import com.sophon.logger.slog;
import com.sophon.util.TimeCalc;

import java.io.*;

/**
 * @Author tiansheng
 * @Date 2019/8/25 1:32
 * @Description TODO
 */
public class Test {

    public static void main(String[] args) throws IOException {
        TimeCalc.invoke(Test.class, "test");
    }

    public void test() throws Exception {
        for (int i = 0; i < 10000; i++) {
            slog.info("HelloWorld[{}]", i);
        }
        /*Scanner s = new Scanner(System.in);
        while (true) {
            String v = s.next();
            slog.debug(v);
        }*/
    }

}
