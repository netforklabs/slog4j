package com.meranti.io;

import com.meranti.config.ConfigVo;

import java.io.*;
import java.util.ArrayList;
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

    static {

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
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
        List<String> v = new ArrayList<>();
        File[] listFiles = file.getParentFile().listFiles();
        String sourceFile = ConfigVo.getLoggerPrintPath();
        // 后缀
        String suffix = sourceFile.substring(sourceFile.lastIndexOf("/"))
                .replace("/", "");
        suffix = suffix.substring(suffix.lastIndexOf("."));
        for (File dir : listFiles) {
            String s = dir.toString();
            v.add(s.substring(s.lastIndexOf("\\"))
                    .replace("\\", "")
                    .replace(suffix, "")
            );
        }
        // 判断文件名是否存在数字
        if (v.isEmpty() != true && v.size() == 1) {
            String nums = v.get(0);
            nums = nums.substring(nums.length() - 1);
            Pattern pattern = Pattern.compile("[0-9]");
            Matcher matcher = pattern.matcher(nums);
            if(!matcher.matches()){
                System.out.println("不是数字");
            }else{
                System.out.println("是数字");
            }
        }
    }

    public static void main(String[] args) {
        newLogFile();
    }

}

