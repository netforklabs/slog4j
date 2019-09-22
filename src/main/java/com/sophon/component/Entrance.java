package com.sophon.component;

import com.sophon.component.exception.UncheckedExceptionHandler;

/**
 * @Author tiansheng
 * @Date 2019/9/14 4:10
 * @Description 框架初始化操作
 */
public class Entrance {

    /**
     * 对所有实现了SophonInit接口的类进行初始化
     */
    public static void frameworkInit(){
        new UncheckedExceptionHandler().init();
        /*Slog4jConfiguration.getInstance();
        ArrayList<Class<?>> clesses = SophonUtils.getInterfaceImpls(SophonInit.class);
        try{
            for(Class<?> v : clesses){
                Method method = v.getMethod(SophonInit.class.getMethods()[0].getName());
                method.invoke(v.newInstance());
            }
        }catch (Throwable e){
            e.printStackTrace();
        }*/
    }

}
