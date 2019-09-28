package com.sophon.util;

import java.sql.Ref;
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
public class DateUtils extends Thread{

    public String time = "";

    private static DateUtils du;

    private DateUtils() {
        // 先执行generate()确保一定可以获取时间
        generate();
        super.start();
    }

    public static String getTime() {
        if(du == null){
            du = new DateUtils();
        }
        return du.time;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(59 * 1000);
                generate();
            } catch (InterruptedException IE) {
                IE.printStackTrace();
            }
        }
    }

    private void generate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String time = dateFormat.format(date);
        setTime(time);
    }

    private void setTime(String time) {
        this.time = time;
    }

}
