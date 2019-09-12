package com.sophon.component.security;

import com.sophon.component.Prompt;
import com.sophon.component.io.SophonFile;
import com.sophon.exception.PathInvalidException;
import com.sophon.logger.Logger;
import com.sophon.logger.SystemLogger;

import java.io.File;

/**
 * @Author tiansheng
 * @Date 2019/9/12 23:37
 * @Description 安全管理检查组件
 */
public class SecurityManager implements Security {

    /**
     * 私有构造器,不允许该对象进行实例
     */
    private SecurityManager() {
    }

    /**
     * 安全检查模块
     */
    private static Security security;

    static {
        security = new SecurityManager();
    }

    /**
     * 获取安全检查对象
     *
     * @return
     */
    public static Security getSecurityManager() {
        return security;
    }

}
