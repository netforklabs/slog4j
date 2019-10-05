package com.sophon.component.security;

import com.sophon.util.StringUtils;

/**
 * 安全检查
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/10/5 21:56
 * @since 1.8
 */
public class SecurityManager {

    private SecurityManager(){}

    private static SecurityManager securityManager;

    public static SecurityManager getSecurityManager(){
        if(securityManager == null){
            securityManager = new SecurityManager();
        }
        return securityManager;
    }

    /**
     * 判断文件名是否是日志文件名。
     * 衡量标准为：文件名结尾是以“_”加下标结尾的
     * @param filename 文件名
     * @return boolean
     */
    public boolean logfile(String filename){
        String suffix = StringUtils.getSuffix(filename);
        if(suffix == null || !".log".equals(suffix))return false;
        filename = StringUtils.removeSuffix(filename);
        int strlen = filename.length();
        if(StringUtils.isNumber(filename.substring(strlen-1))){
            if("_".equals(filename.substring(strlen-2,strlen-1))){
                return true;
            }
        }
        return false;
    }

}
