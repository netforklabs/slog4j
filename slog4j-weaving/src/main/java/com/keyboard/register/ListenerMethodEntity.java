package com.keyboard.register;

import lombok.Getter;
import lombok.Setter;

/**
 * 将ListenerMethod注解解析后的实体类
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/29 17:14
 * @since 1.8
 */
@Getter
@Setter
public class ListenerMethodEntity {

    /**
     * 文件输出路径
     */
    private String pathname;

    /**
     * 在什么事件下触发
     */
    private String triggerMethod;

    /**
     * 注解所在的类路径
     */
    private String classpath;

    /**
     * 注解所在的方法名
     */
    private String methodname;

    /**
     * 方法参数
     */
    private String[] parameters;

    /**
     * 接口实现类所在的路径
     */
    private String implpath;

}
