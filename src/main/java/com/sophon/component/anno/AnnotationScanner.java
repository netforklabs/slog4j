package com.sophon.component.anno;

import com.sophon.util.SophonUtils;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * 注解扫描器
 *
 * @author 2BKeyboard
 * @version 1.0.0
 * @date 2019/9/26 4:38
 * @since 1.8
 */
public class AnnotationScanner {

    public static List<Class<?>> scanner(Class<? extends Annotation> target, ElementType etype) {
        // 将存在target注解的类放入presences list中
        ArrayList<Class<?>> presences = new ArrayList<>();
        String basePackage = target.getClassLoader().getResource("").getPath();
        File[] files = new File(basePackage).listFiles();
        // 存放class路径的list
        ArrayList<String> classpaths = new ArrayList();
        for (File file : files) {
            // 扫描项目编译后的所有类
            if (file.isDirectory()) {
                SophonUtils.listPackages(file.getName(), classpaths);
            }
        }
        // 根据注解类型扫描
        switch (etype) {
            case TYPE:
                scannerByType(classpaths, presences, target);
                break;
            case FIELD:
                scannerByField(classpaths, presences, target);
                break;
            case METHOD:
                scannerByMethod(classpaths, presences, target);
                break;
            case PACKAGE:
                scannerByPackage(classpaths, presences, target);
                break;
            case TYPE_USE:
                scannerByUseType(classpaths, presences, target);
                break;
            case PARAMETER:
                scannerByParameter(classpaths, presences, target);
                break;
            case CONSTRUCTOR:
                scannerByConstruct(classpaths, presences, target);
                break;
            case LOCAL_VARIABLE:
                scannerByLocalVariable(classpaths, presences, target);
                break;
            case TYPE_PARAMETER:
                scannerByTypeParameter(classpaths, presences, target);
                break;
            case ANNOTATION_TYPE:
                scannerByType(classpaths, presences, target);
                break;
        }
        return presences;
    }


    /**
     * 根据类型扫描，例如：class、enum、interface
     *
     * @param classes   所有类路径
     * @param presences 将存在注解的类添加到presences list中
     */
    private static void scannerByType(List<String> classes, List<Class<?>> presences, Class<? extends Annotation> annoclass) {
        try {
            for (String classpath : classes) {
                Class<?> target = Class.forName(classpath);
                if (target.isAnnotationPresent(annoclass)) {
                    presences.add(target);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据全局对象扫描，例如：private String name
     *
     * @param classes   所有类路径
     * @param presences 将存在注解的类添加到presences list中
     */
    private static void scannerByField(List<String> classes, List<Class<?>> presences, Class<? extends Annotation> annoclass) {
        try {
            for (String classpath : classes) {
                Class<?> target = Class.forName(classpath);
                Field[] fields = target.getDeclaredFields();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(annoclass)) {
                        presences.add(target);
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据方法扫描
     *
     * @param classes   所有类路径
     * @param presences 将存在注解的类添加到presences list中
     */
    private static void scannerByMethod(List<String> classes, List<Class<?>> presences, Class<? extends Annotation> annoclass) {
        try {
            for (String classpath : classes) {
                Class<?> target = Class.forName(classpath);
                Method[] methods = target.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(annoclass)) {
                        presences.add(target);
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据使用类型扫描
     *
     * @param classes   所有类路径
     * @param presences 将存在注解的类添加到presences list中
     */
    private static void scannerByUseType(List<String> classes, List<Class<?>> presences, Class<? extends Annotation> annoclass) {
    }

    /**
     * 根据包名扫描
     *
     * @param classes   所有类路径
     * @param presences 将存在注解的类添加到presences list中
     */
    private static void scannerByPackage(List<String> classes, List<Class<?>> presences, Class<? extends Annotation> annoclass) {
    }

    /**
     * 根据构造函数扫描
     *
     * @param classes   所有类路径
     * @param presences 将存在注解的类添加到presences list中
     */
    private static void scannerByConstruct(List<String> classes, List<Class<?>> presences, Class<? extends Annotation> annoclass) {
        try {
            for (String classpath : classes) {
                Class<?> target = Class.forName(classpath);
                Constructor[] constructors = target.getDeclaredConstructors();
                for (Constructor constructor : constructors) {
                    if (constructor.isAnnotationPresent(annoclass)) {
                        presences.add(target);
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据方法参数扫描
     *
     * @param classes   所有类路径
     * @param presences 将存在注解的类添加到presences list中
     */
    private static void scannerByParameter(List<String> classes, List<Class<?>> presences, Class<? extends Annotation> annoclass) {
        try {
            for (String classpath : classes) {
                Class<?> target = Class.forName(classpath);
                Method[] methods = target.getDeclaredMethods();
                for (Method method : methods) {
                    Parameter[] parameters = method.getParameters();
                    for (Parameter parameter : parameters) {
                        if (parameter.isAnnotationPresent(annoclass)) {
                            presences.add(target);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据类型参数声明扫描
     *
     * @param classes   所有类路径
     * @param presences 将存在注解的类添加到presences list中
     */
    private static void scannerByTypeParameter(List<String> classes, List<Class<?>> presences, Class<? extends Annotation> annoclass) {
    }

    /**
     * 根据局部变量扫描
     *
     * @param classes   所有类路径
     * @param presences 将存在注解的类添加到presences list中
     */
    private static void scannerByLocalVariable(List<String> classes, List<Class<?>> presences, Class<? extends Annotation> annoclass) {
    }

    /**
     * 根据注解对象扫描
     *
     * @param classes   所有类路径
     * @param presences 将存在注解的类添加到presences list中
     */
    private static void scannerByAnnotationType(List<String> classes, List<Class<?>> presences, Class<? extends Annotation> annoclass) {
    }

}
