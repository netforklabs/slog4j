package com.meranti.config;


import com.meranti.util.StringUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author Mr.luo
 * @Date 2019/8/25 13:03
 * @Description 加载配置
 */
public class ConfigVo {

    private static ResourceBundle bundle = null;

    static {
        bundle = ResourceBundle.getBundle("mlog4j");
    }

    /**
     * 防止中文乱码
     *
     * @param key
     * @return
     */
    private static String getValue(String key) {
        String v = null;
        try {
            v = new String(bundle.getString(key).getBytes("ISO-8859-1"), "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return v;
    }

    /**
     * logger 文件输出路径
     */
    private static final String LOGGER_PRINT_PATH = getValue("logger.print.path");

    /**
     * logger 输出模板
     */
    private static final String LOGGER_PRINT_TEMPLATE = getValue("logger.print.template");

    /**
     * logger 文件生成规则,按天数还是按大小
     */
    private static final String LOGGER_GENERATE_RULE = getValue("logger.generate.rule");

    /**
     * 禁止某个级别的日志输出到控制台
     */
    private static final String LOGGER_PROHIBIT_LEVEL_CONSOLE = getValue("logger.prohibit.level.console");

    /**
     * 禁止某个级别的日志输出到文件
     */
    private static final String LOGGER_PROHIBIT_LEVEL_FILE = getValue("logger.prohibit.level.file");

    /**
     * 获取日期格式化配置
     *
     * @return
     */
    public static SimpleDateFormat getSimpleDateFormat() {
        String v = LOGGER_PRINT_TEMPLATE;
        // 判断有没有配置日期格式化规则
        if (StringUtils.isExist(v, "\\$\\{datetime:(.*?)\\}")) {
            Pattern pattern = Pattern.compile("\\$\\{(.*?)\\}");
            Matcher matcher = pattern.matcher(v);
            matcher.find();
            String datetime = matcher.group(1).replaceAll("datetime:", "");
            return new SimpleDateFormat(datetime);
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取日志打印模板
     *
     * @return
     */
    public static String getLoggerPrintTemplate() {
        String v = LOGGER_PRINT_TEMPLATE;
        v = v.replaceAll("\\$\\{datetime:(.*?)\\}","\\$\\{datetime\\}");
        return v;
    }

    public static void main(String[] args) {
        System.out.println(getLoggerPrintTemplate());
    }

}
