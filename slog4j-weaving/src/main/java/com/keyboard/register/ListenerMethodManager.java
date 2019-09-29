package com.keyboard.register;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * <p>ListenerMethodEntity管理器，负责管理这些对象的生命周期。</p>
 * <p>
 * 如果注解够多的话可能会导致内存的浪费，所以有此类对ListenerMethodEntity对象管理。
 * 它们会在agent使用完后进行销毁。
 * </p>
 *
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/29 17:33
 * @since 1.8
 */
public class ListenerMethodManager {

    /**
     * 存放实例
     */
    private static Hashtable<String, ListenerMethodEntity> entityTable = new Hashtable<>();


    /**
     * 添加ListenerMethodEntity
     *
     * @param k key是ListenerMethodEntity中的classpath
     * @param v value就是ListenerMethodEntity对象了
     */
    public static void put(String k, ListenerMethodEntity v) {
        entityTable.put(v.getClasspath(), v);
    }

    /**
     * 获取ListenerMethodEntity
     *
     * @param k key是ListenerMethodEntity中的classpath
     */
    public static ListenerMethodEntity get(String k) {
        return entityTable.get(k);
    }

}
