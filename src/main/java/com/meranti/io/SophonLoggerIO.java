package com.meranti.io;

import com.meranti.config.ConfigVo;

import java.io.*;

/**
 * @Author Mr.luo
 * @Date 2019/8/26 11:21
 * @Description 文件工具类
 */
public class SophonLoggerIO {

    //private static final File file = new File(ConfigVo.getLoggerPrintPath());
    private static final File file = new File(
            System.getProperty("user.dir") + "/logs/slog.log");
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
    public static void newLogFile(){
        File[] listFiles = file.listFiles();
        for(File dir : listFiles){
            System.out.println(dir);
        }
    }

    public static void main(String[] args) {
        newLogFile();
    }

}

