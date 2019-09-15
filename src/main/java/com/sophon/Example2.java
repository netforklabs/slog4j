package com.sophon;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * @Author tiansheng
 * @Date 2019/9/15 22:35
 * @Description TODO
 */
public class Example2 {

    public static String template = "${datetime} ${class} | ${method}:${line} - [${level}]: ";

    public static void main(String[] args) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder(template);
        sb.replace(0,11,"9102-08-30");
        System.out.println(sb);
    }

}
