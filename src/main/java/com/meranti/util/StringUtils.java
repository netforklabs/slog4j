package com.meranti.util;

/**
 * @Author Mr.luo
 * @Date 2019/8/23 23:52
 * @Description String工具类
 */
public class StringUtils {

    /**
     * 字符串是否为空
     * @param s
     * @return
     */
    public static boolean isEmpty(String s){
        if(s == null){
            return true;
        }
        // Unicode 编码下的空格
        if("\u0000".equals(s)){
            return true;
        }
        return s.length() == 0 || "".equals(s);
    }

}
