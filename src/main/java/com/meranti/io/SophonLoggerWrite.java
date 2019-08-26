package com.meranti.io;

import com.meranti.config.ConfigVo;

import java.util.Vector;

/**
 * @Author Mr.luo
 * @Date 2019/8/25 13:00
 * @Description 日志文件管理工具
 */
public class SophonLoggerWrite {

    /**
     * 日志记录写出规则,按天数或按大小
     */
    private static final String[] rule = ConfigVo.getLoggerGenerateRule();

    /**
     * 文件输出路径
     */
    private static String writePath = ConfigVo.getLoggerPrintPath();

    /**
     * 数据写出接口
     */
    private static AbsSophonWrite loggerWrite = null;

    static {
        // 根据规则判断选择哪种方式生成
        switch (rule[0]) {
            case ConfigVo.BY_DAYS:
                loggerWrite = new SophonWriteByDays();
                break;
            case ConfigVo.BY_SIZE:
                loggerWrite = new SophonWriteBySize();
                break;
        }
    }

    /**
     * 输出
     * @param v
     */
    public static void write(String v){
        loggerWrite.write(v);
    }

}
