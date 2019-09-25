package com.sophon.util;

import com.sophon.component.exception.ParamException;
import com.sophon.component.io.SophonFile;
import future.anno.ListenerMethod;


import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * Sophon Logger 框架工具类
 * @author tiansheng
 * @date 2019/9/14 4:17
 * @version 1.0.0
 * @since 1.8
 */
public class SophonUtils {

    /**
     * 获取一个接口的所有实现类
     *
     * @param target 传入目标接口的class对象
     * @return 返回该接口的所有实现类的类对象
     */
    @ListenerMethod
    public static ArrayList<Class<?>> getInterfaceImpls(Class<?> target) {
        ArrayList<Class<?>> subclasses = new ArrayList();
        try {
            // 判断class对象是否是一个接口
            if (target.isInterface()) {
                String basePackage = target.getClassLoader().getResource("").getPath();
                File[] files = new File(basePackage).listFiles();
                // 存放class路径的list
                ArrayList<String> classpaths = new ArrayList();
                for (File file : files) {
                    // 扫描项目编译后的所有类
                    if (file.isDirectory()) {
                        listPackages(file.getName(), classpaths);
                    }
                }
                // 获取所有类,然后判断是否是 target 接口的实现类
                for (String classpath : classpaths) {
                    Class<?> classObject = Class.forName(classpath);
                    if (classObject.getSuperclass() == null) continue; // 判断该对象的父类是否为null
                    Set<Class<?>> interfaces = new HashSet<>(Arrays.asList(classObject.getInterfaces()));
                    if (interfaces.contains(target)) {
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
     * 获取项目编译后的所有的.class的字节码文件
     * 这么做的目的是为了让 Class.forName() 可以加载类
     *
     * @param basePackage 默认包名
     * @param classes     存放字节码文件路径的集合
     */
    public static void listPackages(String basePackage, List<String> classes) {
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

    /**
     * 创建新的BufferedWriter IO流
     *
     * @param file SophonFile文件对象
     * @return 返回一个BUfferedWriter输出流
     * @throws FileNotFoundException 文件找不到
     */
    public static BufferedWriter newBufferedWriter(SophonFile file) throws FileNotFoundException {
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
    }

}
