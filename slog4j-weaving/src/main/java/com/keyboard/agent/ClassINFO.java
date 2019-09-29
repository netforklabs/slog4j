package com.keyboard.agent;

import java.io.Serializable;
import java.util.Set;

/**
 * 利用Agent获取到的一些信息存在这个类，这个类可以被序列化。
 *
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/30 3:04
 * @since 1.8
 */
public class ClassINFO implements Serializable {

    /**
     * 已经被加载过的类（只包含被加载的类，如果类被卸载了数据保持不变）
     */
    private static Set<String> alreadyLoaders;

    /**
     * 添加一个已被加载过的类
     *
     * @param classpath 类路径
     */
    public static void setLoaderClass(String classpath) {
        alreadyLoaders.add(classpath);
    }

    /**
     * 获取已经被加载过的类集合
     *
     * @return 返回Set集合
     */
    public static Set<String> getAlreadyLoaders() {
        return alreadyLoaders;
    }

}
