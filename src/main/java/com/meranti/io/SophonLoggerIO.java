package com.meranti.io;

import com.meranti.config.ConfigVo;
import com.meranti.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author Mr.luo
 * @Date 2019/8/26 11:21
 * @Description 文件工具类
 */
public class SophonLoggerIO {

    private static final File file = new File(
            System.getProperty("user.dir") + ConfigVo.getLoggerPrintPath());
    private static BufferedWriter out = null;

    /**
     * 文件名
     */
    private static String logFileName = null;

    /**
     * 文件后缀
     */
    private static String logFileSuffix = null;

    /**
     * 文件路径,不带文件名
     */
    private static String filePath = null;

    static {

        try {
            // 获取后缀
            String sourceFile = ConfigVo.getLoggerPrintPath();
            String suffix = sourceFile.substring(sourceFile.lastIndexOf("/"))
                    .replace("/", "");
            logFileSuffix = suffix.substring(suffix.lastIndexOf("."));
            // 获取文件名
            String s = file.toString();
            logFileName = s.substring(s.lastIndexOf("\\"))
                    .replace("\\", "")
                    .replace(suffix, "");
            // 获取文件路径
            filePath = file.getParentFile().toString().concat("/");
            newLogFile();
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取当前文件有多少kb
     *
     * @return
     */
    public static Long getSizeByKB() {
        return file.length() / 1024;
    }

    /**
     * 写出数据
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
     * 创建新文件
     */
    public static void newLogFile() {
        // 在每次创建文件前,判断当前文件是否存在,不存在则创建
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        List<String> v = new ArrayList<>();
        String filename = ""; // 新文件名
        // 扫描当前文件夹下的所有文件,找出最新的
        File[] listFiles = file.getParentFile().listFiles();
        for (File dir : listFiles) {
            String name = dir.toString();
            name = name.substring(name.lastIndexOf("\\"))
                    .replace("\\", "")
                    .replace(logFileSuffix, "");
            // 只加载文件名中带有指定字符的文件名
            if (StringUtils.isExist(name, logFileName)) {
                v.add(name);
            }
        }
        // 对文件名排序
        Collections.reverse(v);
        if (!v.isEmpty()) {
            filename = v.get(0);
            // 取最后一位字符判断是不是数字
            String lastChar = filename.substring(filename.length() - 1);
            Pattern pattern = Pattern.compile("[0-9]");
            Matcher matcher = pattern.matcher(lastChar);
            if (matcher.matches()) {
                // 如果是数字则加1
                int index = Integer.parseInt(lastChar);
                filename = filename.substring(0,filename.length()-1);
                System.out.println(filename+(index+1)+logFileSuffix);
            } else {
                // 如果不是数字则判断 v list中是否存在符合logFileName的文件名
                System.out.println(filename);
                System.out.println("not number");
            }
        }
    }

    public static void main(String[] args) {
        newLogFile();
    }

}

