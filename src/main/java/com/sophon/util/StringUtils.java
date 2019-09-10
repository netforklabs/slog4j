package com.sophon.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author tiansheng
 * @Date 2019/8/23 23:52
 * @Description String工具类
 */
public class StringUtils {

    /**
     * 字符串是否为空
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        if (s == null) {
            return true;
        }
        // Unicode 编码下的空格
        if ("\u0000".equals(s)) {
            return true;
        }
        return s.length() == 0 || "".equals(s);
    }

    /**
     * 是否存在某个字符串
     *
     * @param s     字符串
     * @param regex 需要查找的字符串(支持正则)
     * @return
     */
    public static boolean isExist(String s, String regex) {
        if (s.contains(regex)) return true;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }

    /**
     * 获取最后一个字符
     *
     * @param v
     * @return
     */
    public static String getLastString(String v) {
        return v.substring(v.length() - 1);
    }

    /**
     * 删除最后一个字符
     *
     * @param v
     * @return
     */
    public static String removeLastString(String v) {
        return v.substring(0,v.length() - 1);
    }

    /**
     * 判断当前字符串是不是数字
     * @param v
     * @return
     */
    public static boolean isNumber(String v){
        return v.matches("^[0-9]*$");
    }

    /**
     * 删除所有非数字的字符
     * @param v
     * @return
     */
    public static String removeNotNumber(String v){
        return v.replaceAll("[^\\d]","");
    }

    /**
     * 获取两个字符之前的数字
     * 从右到左截取。
     * @param v 原字符串
     * @param var1 第一个字符
     * @param var2 第二个字符
     * @return
     */
    public static String getS2SChars(String v,String var1,String var2){
        return v.substring(v.lastIndexOf(var1)+1,v.lastIndexOf(var2));
    }

}
