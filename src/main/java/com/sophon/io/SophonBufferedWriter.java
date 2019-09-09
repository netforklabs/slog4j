package com.sophon.io;

import com.sophon.logger.SophonLogger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedList;
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
     * volatile关键字会使两个线程的数据及时更新
     */
    static volatile Vector<String> logs = new Vector<>();

    /**
     * 当前是否在更新out对象
     */
    private volatile boolean isUpdateOut = false;

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
    }

    @Override
    public void run() {
        try {
            while (true) {
                while (isNext()) {
                    /* Iterator接口能够防止在遍历的时候List又在新增从而出现错误的情况 */
                    Iterator<String> iterator = logs.iterator();
                    while (iterator.hasNext()) {
                        out.write(iterator.next());
                        iterator.remove();
                    }
                    out.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * 当前状态是否可以写入
     *
     * @return
     */
    public boolean isNext() {
        return !isUpdateOut && !logs.isEmpty();
    }

    /**
     * 重新实例化out流
     */
    public void reNewIOInstance() {
        // 判断文件是否存在,不存在则创建
        SophonFiles file = SophonLoggerIO.getFile();
        if (!file.exists()) {
            file.create();
            createWriter(file);
        } else {
            int number = SophonLoggerIO.getNewestLoggerFile();
            if (number > 0) {
                createWriter(new File(
                        file.getParent()
                                .concat(file.getNoSuffixName())
                                .concat(String.valueOf(number))
                                .concat(SophonLoggerIO.getSuffix())
                ));
            } else if (number == 0) {
                createWriter(file);
            }
        }
    }

}
