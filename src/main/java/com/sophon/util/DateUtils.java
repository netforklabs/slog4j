package com.sophon.util;

import java.sql.Ref;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <h3>slog4j</h3>
 * <p>带缓存的时间解析工具，减轻每次执行都要重新获取时间的性能损失</p>
 *
 * @author : https://github.com/AdlerED
 * @date : 2019-09-27 09:58
 **/
public class DateUtils {
    public static String time = "";
    private static Refresh refresh = new Refresh();

    private DateUtils() {}

    public static String getTime() {
        return time;
    }

    public static void stop() {
        refresh.stop();
    }
}

class Refresh extends Thread {
    public Refresh() {
        // 先执行generate()确保一定可以获取时间
        generate();
        super.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                int sleepTimeBySecond = 10;
                sleepTimeBySecond = sleepTimeBySecond * 1000;
                Thread.sleep(sleepTimeBySecond);
                generate();
            } catch (InterruptedException IE) {
                IE.printStackTrace();
            }
        }
    }

    private void generate() {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMdd");
        String time = dateFormat.format(date);
        setTime(time);
    }

    private void setTime(String time) {
        DateUtils.time = time;
    }
}
