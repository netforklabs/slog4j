package com.sophon.config;


import com.sophon.component.Entrance;
import com.sophon.logger.SophonLogger;
import com.sophon.util.StringUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author tiansheng
 * @Date 2019/8/25 13:03
 * @Description 加载配置(单例模式 - 懒汉式)
 */
public class Slog4jConfiguration {

    public static final String pathPrefix = "classpath:";

    private static Slog4jConfiguration cv;

    private static Properties config;

    private Slog4jConfiguration() {
    }

    /**
     * 选择手动加载配置文件
     *
     * @param pathname
     */
    public static void loadProperties(String pathname) {
        try {
            String classpath = Slog4jConfiguration.pathPrefix;
            if (classpath.equals(pathname.substring(0, classpath.length()))) {
                pathname = pathname.replaceAll(classpath, "");
                pathname = System.getProperty("user.dir").concat(pathname);
                pathname = pathname.replaceAll("/", "\\\\");
            }
            config = new Properties();
            InputStream stream = new FileInputStream(pathname);
            config.load(stream);
            stream.close();
            Entrance.frameworkInit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取对象实例
     *
     * @return
     */
    public static Slog4jConfiguration getInstance() {
        if (cv == null) {
            if (config == null) {
                try {
                    config = new Properties();
                    String path = System.getProperty("user.dir") + "\\src\\main\\resources\\slog4j.properties";
                    // 当前目录文件不存在就到其他目录下去寻找
                    if(!new File(path).exists()){
                        if(!new File(path).exists()){
                            path = System.getProperty("user.dir") + "\\resources\\slog4j.properties";
                            if(!new File(path).exists()){
                                throw new NullPointerException("如果要使用自动扫描配置文件功能,请将文件放在resources目录下");
                            }
                        }
                    }
                    InputStream is = new FileInputStream(path);
                    config.load(is);
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            cv = new Slog4jConfiguration();
            //
            // configvo类做初始化工作
            // 因为通常来说,配置类是最先加载的,所以在ConfigVo中做初始化操作
            //
            Entrance.frameworkInit();
        }
        return cv;
    }

    /**
     * 防止中文乱码
     *
     * @param key
     * @return
     */
    private String getValue(String key) {
        String v = null;
        try {
            v = new String(config.getProperty(key).getBytes("ISO-8859-1"), "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return v;
    }

    // 统计写出了多少条数据
    private long writeCount = 0L;
    // 统计打印了多少条数据
    private long printCount = 0L;

    public void writePlus() {
        writeCount++;
    }

    public void printPlus() {
        printCount++;
    }

    public long getWriteCount() {
        return writeCount;
    }

    public long getPrintCount() {
        return printCount;
    }

    /**
     * logger 文件输出路径
     */
    private final String LOGGER_PRINT_PATH = getValue("logger.print.path");

    /**
     * 框架级别的日志输出路径
     */
    private final String LOGGER_SYSTEM_PRINT_PATH = getValue("logger.system.print.path");

    /**
     * 框架捕获到的异常输出路径
     */
    private final String LOGGER_SYSTEM_PRINT_CAPTURE_EXCEPTION_PATH
            = getValue("logger.system.print.capture.exception.path");

    /**
     * logger 输出模板
     */
    private final String LOGGER_PRINT_TEMPLATE = getValue("logger.print.template");

    /**
     * logger 文件生成规则,按天数还是按大小
     */
    public final String BY_SIZE = "size";
    private final String LOGGER_GENERATE_RULE = getValue("logger.generate.rule");

    /**
     * 禁止某个级别的日志输出到控制台
     */
    private final String LOGGER_PROHIBIT_LEVEL_CONSOLE = getValue("logger.prohibit.level.console");

    /**
     * 禁止某个级别的日志输出到文件
     */
    private final String LOGGER_PROHIBIT_LEVEL_FILE = getValue("logger.prohibit.level.file");

    /**
     * 国家语言
     */
    private final String SLOG4j_SYSTEM_PROMPT_LANGUAGE = getValue("slog4j.system.prompt.language");

    /**
     * 是否将日志输出到文件
     */
    private final String LOGGER_PRINT_WRITE = getValue("logger.print.write");

    /**
     * 获取日期格式化配置
     *
     * @return
     */
    public SimpleDateFormat getSimpleDateFormat() {
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
    public String getLoggerPrintTemplate() {
        String v = LOGGER_PRINT_TEMPLATE;
        v = v.replaceAll("\\$\\{datetime:(.*?)\\}", "\\$\\{datetime\\}");
        return v;
    }

    public String getLoggerPrintPath() {
        return LOGGER_PRINT_PATH;
    }

    /**
     * 文件生成规则
     *
     * @return
     */
    public String[] getLoggerGenerateRule() {
        String[] v = new String[2];
        String rule = LOGGER_GENERATE_RULE;
        return rule.split(":");
    }

    public String getSlog4jSystemPromptLanguage() {
        return System.getProperty("user.dir")
                .concat("\\language\\")
                .concat(SLOG4j_SYSTEM_PROMPT_LANGUAGE);
    }

    public boolean getLoggerPrintWrite() {
        switch (LOGGER_PRINT_WRITE) {
            case "true":
                return true;
            case "false":
                return false;
            default:
                return true;
        }
    }

    public String getLoggerSystemPrintCaptureExceptionPath() {
        return LOGGER_SYSTEM_PRINT_CAPTURE_EXCEPTION_PATH;
    }

    public String getLoggerSystemPrintPath() {
        return LOGGER_SYSTEM_PRINT_PATH;
    }

    /**
     * 被禁止输出到控制台的级别
     *
     * @return
     */
    public Set<SophonLogger.Level> getLoggerProhibitLevelConsole() {
        Set<SophonLogger.Level> set = new HashSet<>();
        if ("no".equals(LOGGER_PROHIBIT_LEVEL_FILE)) return set;
        String[] levels = LOGGER_PROHIBIT_LEVEL_CONSOLE.split(",");
        for (String level : levels) {
            if (level.equals(SophonLogger.Level.INFO.toString())) set.add(SophonLogger.Level.INFO);
            if (level.equals(SophonLogger.Level.DEBUG.toString())) set.add(SophonLogger.Level.DEBUG);
            if (level.equals(SophonLogger.Level.ERROR.toString())) set.add(SophonLogger.Level.ERROR);
            if (level.equals(SophonLogger.Level.WARN.toString())) set.add(SophonLogger.Level.WARN);
        }
        return set;
    }

    /**
     * 被禁止输出到文件的级别
     *
     * @return
     */
    public Set<SophonLogger.Level> getLoggerProhibitLevelFile() {
        Set<SophonLogger.Level> set = new HashSet<>();
        if ("no".equals(LOGGER_PROHIBIT_LEVEL_FILE)) return set;
        String[] levels = LOGGER_PROHIBIT_LEVEL_FILE.split(",");
        for (String level : levels) {
            if (level.equals(SophonLogger.Level.INFO.toString())) set.add(SophonLogger.Level.INFO);
            if (level.equals(SophonLogger.Level.DEBUG.toString())) set.add(SophonLogger.Level.DEBUG);
            if (level.equals(SophonLogger.Level.ERROR.toString())) set.add(SophonLogger.Level.ERROR);
            if (level.equals(SophonLogger.Level.WARN.toString())) set.add(SophonLogger.Level.WARN);
        }
        return set;
    }

    public void main(String[] args) {
        System.out.println(getLoggerPrintTemplate());
    }

}
