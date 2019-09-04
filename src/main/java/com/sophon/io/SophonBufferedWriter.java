package com.sophon.io;

import com.google.common.collect.Lists;

import java.io.*;
import java.nio.Buffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * @Author Mr.luo
 * @Date 2019/9/1 20:51
 * @Description TODO
 */
public class SophonBufferedWriter extends Thread {

    public SophonBufferedWriter() {
        this.setName("logger write");
        this.start();
    }

    /**
     * 日志缓冲区
     */
    static Vector<String> logs = new Vector<>();

    /**
     * 当前是否在更新out对象
     */
    private boolean isUpdateOut = false;

    private BufferedWriter out = null;

    /**
     * 创建输出流
     *
     * @param file
     */
    public void createWriter(File file) {
        try {
            isUpdateOut = true;
            if (out != null) {
                out.close();
            }
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)
            ));
            isUpdateOut = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加一条日志记录
     *
     * @param log
     */
    public void add(String log) {
        logs.add(log.concat("\n"));
        System.out.println(logs.size());
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (!isUpdateOut && !logs.isEmpty()) {
                    for (String log : logs) {
                        out.write(log);
                        logs.remove(0);
                    }
                    out.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
