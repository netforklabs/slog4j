package com.sophon.io;

import com.google.common.collect.Lists;

import java.io.*;
import java.nio.Buffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * @Author tiansheng
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
    static LinkedList<String> logs = new LinkedList<>();

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
            do {
                while (isNext()) {
                    System.out.println("-------------->");
                    /* Iterator接口能够防止在遍历的时候List又在新增从而出现错误的情况 */
                    Iterator<String> iterator = logs.iterator();
                    while (iterator.hasNext()) {
                        out.write(iterator.next());
                        iterator.remove();
                    }
                    out.flush();
                }
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public boolean isNext() {
        return !isUpdateOut && !logs.isEmpty();
    }

}
