package com.meranti.io;

import com.meranti.config.ConfigVo;
import com.meranti.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Author Mr.luo
 * @Date 2019/8/26 11:21
 * @Description 文件工具类
 */
public class SophonLoggerIO {

    private static SophonFiles file = new SophonFiles(
            System.getProperty("user.dir") + ConfigVo.getLoggerPrintPath());

    private static BufferedWriter out = null;

    /**
     * 日志记录写出规则,按天数或按大小
     */
    private static final String[] rule = ConfigVo.getLoggerGenerateRule();

    /**
     * 文件输出路径
     */
    private static String writePath = ConfigVo.getLoggerPrintPath();

    /**
     * 数据写出接口
     */
    private static SophonWrite loggerWrite = null;

    static {
        // 根据规则判断选择哪种方式生成
        switch (rule[0]) {
            case ConfigVo.BY_DAYS:
                loggerWrite = new SophonWriteByDays();
                break;
            case ConfigVo.BY_SIZE:
                loggerWrite = new SophonWriteBySize();
                break;
        }
        // 开启IO流
            /*out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)
            ));*/
    }

    public static long getSize() {
        return file.getSizeByKB();
    }

    /**
     * 数据写出方法
     *
     * @param v
     */
    public static void write(String v) {
        try {
            out.write(v.concat("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建新的log文件
     */
    public static void createFile() {
        ArrayList<String> names = file.getFileNamesByFolder();
        System.out.println(names.isEmpty());
    }

    public static void main(String[] args) {
        createFile();
    }

}

