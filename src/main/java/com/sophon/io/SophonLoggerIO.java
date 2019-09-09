package com.sophon.io;

import com.sophon.config.ConfigVo;
import com.sophon.util.StringUtils;

import java.io.*;
import java.util.ArrayList;

/**
 * @Author tiansheng
 * @Date 2019/8/26 11:21
 * @Description 文件工具类
 */
public class SophonLoggerIO {

    private static SophonFiles file = new SophonFiles(
            System.getProperty("user.dir") + ConfigVo.getLoggerPrintPath());

    private static SophonWriteIO out = new SophonWriteIO();

    /**
     * 日志记录写出规则,按天数或按大小
     */
    private static final String[] rule = ConfigVo.getLoggerGenerateRule();

    private static final String suffix = file.getSuffix();

    /**
     * 数据写出接口
     */
    private static SophonWrite loggerWrite = null;
    public static SophonWrite getLoggerWrite(){
        return loggerWrite;
    }

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
        // 判断文件是否存在,不存在则创建
        if (!file.exists()) {
            file.create();
            out.createWriter(file);
        } else {
            int number = getNewestLoggerFile();
            if (getNewestLoggerFile() > 0) {
                out.createWriter(new File(
                        file.getParent()
                                .concat(file.getNoSuffixName())
                                .concat(String.valueOf(number))
                                .concat(suffix)
                ));
            } else if (number == 0) {
                out.createWriter(file);
            }
        }
        out.start();
    }

    /**
     * 获取当前文件大小
     *
     * @return
     */
    public static long getSize() {
        return file.getSizeByKB();
    }

    /**
     * 创建新的log文件
     */
    public static void createFile() {
        String number = String.valueOf(getNewestLoggerFile() + 1);
        file.create(file.getNoSuffixName().concat(number).concat(suffix));
        out.createWriter(new File(
                file.getParent()
                        .concat(file.getNoSuffixName())
                        .concat(number)
                        .concat(suffix)
        ));
    }

    /**
     * 添加数据
     */
    public static void write(String v) {
        out.add(v);
    }

    /**
     * 获取文件夹下最新的log文件
     *
     * @return
     */
    public static int getNewestLoggerFile() {
        // 获取当前文件夹下的所有文件名
        ArrayList<String> names = file.getFileNamesByFolder();
        int endNumber = 0; // 记录创建到了第几个日志文件了
        for (String name : names) {
            String lastString = StringUtils.getLastString(name);
            if (StringUtils.isNumber(lastString)) {
                int lastStringToInt = Integer.parseInt(lastString);
                // 如果是创建的第2个文件的话,那么就命名为xxx2.log
                // 因为第一个文件的命名为 xxx.log, 第二个的为 xxx1.log
                if (lastStringToInt > endNumber) {
                    endNumber = lastStringToInt;
                }
            } else {
                continue;
            }
        }
        return endNumber;
    }

    public static SophonFiles getFile(){
        return file;
    }

    public static String getSuffix(){
        return suffix;
    }

}

