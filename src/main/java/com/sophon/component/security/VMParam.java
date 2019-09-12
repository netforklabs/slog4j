package com.sophon.component.security;

/**
 * @Author tiansheng
 * @Date 2019/9/13 2:07
 * @Description 系统参数
 */
public class VMParam {

    /**
     * 获取当前操作系统
     * @return OS
     */
    public static OS getOS() {
        String osname = System.getProperty("os.name").toLowerCase();
        if (osname.contains("windows")) {
            return OS.WINDOWS;
        }
        return OS.UNIX;
    }

}
