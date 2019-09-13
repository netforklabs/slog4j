package com.sophon.util;

import com.google.common.collect.Lists;
import com.sophon.component.SophonInit;
import com.sophon.component.exception.ParamException;
import com.sophon.logger.SystemLogger;
import com.sun.istack.internal.NotNull;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author tiansheng
 * @Date 2019/9/14 4:17
 * @Description Sophon Logger for Java 日志框架工具类
 */
public class SophonUtils {

    /**
     * 获取一个接口的所有实现类
     *
     * @param target
     * @return
     */
    public static ArrayList<Class<?>> getInterfaceImpls(Class<?> target) {
        ArrayList<Class<?>> subclasses = Lists.newArrayList();
        try {
            // 判断class对象是否是一个接口
            if (target.isInterface()) {
                @NotNull
                String basePackage = target.getClassLoader().getResource("").getPath();
                File[] files = new File(basePackage).listFiles();
                // 存放class路径的list
                ArrayList<String> classpaths = Lists.newArrayList();
                for (File file : files) {
                    // 扫描项目编译后的所有类
                    if (file.isDirectory()) {
                        listPackages(file.getName(), classpaths);
                    }
                }
                // 获取所有类,然后判断是否是 target 接口的实现类
                for (String classpath : classpaths) {
                    Class<?> classObject = Class.forName(classpath);
                    if(classObject.getSuperclass() == null) continue; // 判断该对象的父类是否为null
                    Set<Class<?>> interfaces = new HashSet<>(Arrays.asList(classObject.getInterfaces()));
                    if(interfaces.contains(target)){
                        subclasses.add(Class.forName(classObject.getName()));
                    }
                }
            } else {
                throw new ParamException("Class对象不是一个interface");
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return subclasses;
    }

    /**
     * 获取编译后的所有类路径
     *
     * @param basePackage
     * @param classes
     * @return
     */
    public static void listPackages(String basePackage, ArrayList<String> classes) {
        URL url = SophonUtils.class.getClassLoader()
                .getResource("./" + basePackage.replaceAll("\\.", "/"));
        File directory = new File(url.getFile());
        for (File file : directory.listFiles()) {
            // 如果是一个目录就继续往下读取(递归调用)
            if (file.isDirectory()) {
                listPackages(basePackage + "." + file.getName(), classes);
            } else {
                // 如果不是一个目录,判断是不是以.class结尾的文件,如果不是则不作处理
                String classpath = file.getName();
                if (".class".equals(classpath.substring(classpath.length() - ".class".length()))) {
                    classes.add(basePackage + "." + classpath.replaceAll(".class", ""));
                }
            }
        }
    }

    public static void main(String[] args) {
        //
        // 注意这个class对象是个接口,List中包含的是SophonInit接口的所有实现类
        //
        ArrayList<Class<?>> classes = getInterfaceImpls(SophonInit.class);
    }

}
