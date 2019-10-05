package com.sophon.util;

import com.sophon.config.Slog4jConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <h3>slog4j</h3>
 * <p>带缓存的时间解析工具，用于精确输出，优化并发性能/p>
 *
 * 懒加载模式
 *
 * @author : https://github.com/AdlerED
 * @author : 2BKeyboard
 * @date : 2019-10-03 13:18
 **/
public class TimeUtils{

    private static String time = "";
    private static Long timeStamp = 0l;

    public static String getTime() {
        if (System.currentTimeMillis() - timeStamp >= 1000) {
            generate();
            updateTimeStamp();
        }
        return time;
    }

    private static void updateTimeStamp() {
        timeStamp = System.currentTimeMillis();
    }

    private static void generate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(Slog4jConfiguration.getInstance().getDateFormat());
        time = dateFormat.format(date);
    }

}
