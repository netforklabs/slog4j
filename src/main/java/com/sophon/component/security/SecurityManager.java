package com.sophon.component.security;

import com.sophon.util.StringUtils;

/**
 * 安全管理以及检查，主要针对于可能会发生异常的代码做及时的处理
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/10/1 0:20
 * @since 1.8
 */
public class SecurityManager {

    private static SecurityManager securityManager;

    private SecurityManager(){}

    /**
     * 获取安全检查器
     * @return SecurityManager
     */
    public static SecurityManager getSecurityManager(){
        if(securityManager == null){
            securityManager = new SecurityManager();
        }
        return securityManager;
    }

    /**
     * <p>是不是logger文件。</p>
     * <p>
     *     主要的检测的有以下两点：
     *
     *          --> 是否存在“_”
     *          --> “_”后面是否跟随数字
     * </p>
     * @param filename 文件名
     * @return false=不是，true=是
     */
    public boolean isLoggerFile(String filename){
        if(filename.contains("_")){
            filename = filename.substring(filename.lastIndexOf("_") + 1);
            if(StringUtils.isNumber(filename)) return true;
        }
        return false;
    }

}
