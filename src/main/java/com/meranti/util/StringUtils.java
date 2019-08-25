package com.meranti.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author Mr.luo
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
        if(s.contains(regex)) return true;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }

}
