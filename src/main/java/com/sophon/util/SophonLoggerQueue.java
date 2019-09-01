package com.sophon.util;

import com.sophon.config.ConfigVo;

import java.util.Vector;

/**
 * logger 日志队列
 * <p>
 * 主要作用于系统在生成新的.log文件时
 * 然后将这生成时间段内的日志存入这个队列中,保证log不会丢失
 * <p>
 * 执行流程:
 * <p>
 * |----------事件----------|
 * |   slog.info("hello")  |
 * |-----------------------|
 * |
 * |-----------> 将log放入队列 ---------> 控制台输出 -----
 * |
 * |
 * |---------------> 队列将 log 输出到文件 -----
 * <p>
 * 写入log文件和控制台输出是并行执行的,两者互不干扰
 *
 * @Author Mr.luo
 * @Date 2019/8/26 0:12
 */
public class SophonLoggerQueue extends Thread {

    static {
        new SophonLoggerQueue();
    }

    public SophonLoggerQueue() {
        super.setName("SophonLoggerQueue");
        super.start();
    }

    /**
     * log 存放集合
     */
    private static Vector<String> logs = new Vector<>();

    /**
     * 放入一条log
     *
     * @param v
     */
    public static synchronized void put(String v) {
        logs.add(v);
    }

    /**
     * 消费日志信息
     */
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(50);
                if (logs.isEmpty()) continue;
                for(int i=0,len=logs.size(); i<len; i++){
                    // 消费队列中的数据
                    ConfigVo.writePlus();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
