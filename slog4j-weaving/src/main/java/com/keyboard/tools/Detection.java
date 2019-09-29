package com.keyboard.tools;

import com.keyboard.agent.ClassINFO;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *     获取类的一些信息，主要功能有以下几点：
 *
 *          -- 获取所有已经被加载过的类
 *          -- 获取所有已经被初始化过了的类（执行过了clinit方法，是上面的一个子集）
 *          -- 获取某个对象的大小
 *          -- 将某个jar加入到bootstrapclasspath里作为高优先级被bootstrapClassloader加载
 *          -- 将某个jar加入到classpath里供AppClassloard去加载
 *          -- 设置某些native方法的前缀，主要在查找native方法的时候做规则匹配
 * </p>
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/30 2:53
 * @since 1.8
 */
public class Detection {

    /**
     * 获取已经被加载过的所有类
     * @return 返回Set集合
     */
    public Set<String> getAlreadyLoaderClasses(){
        return ClassINFO.getAlreadyLoaders();
    }

}
