package com.sophon.util;

import com.sophon.config.Slog4jConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <h3>slog4j</h3>
 * <p>带缓存的时间解析工具，减轻每次执行都要重新获取时间的性能损失</p>
 *
 * 懒加载模式
 *
 * @author : https://github.com/AdlerED
 * @author : 2BKeyboard
 * @date : 2019-09-27 09:58
 **/
public class DateUtils{

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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        time = dateFormat.format(date);
    }

}
