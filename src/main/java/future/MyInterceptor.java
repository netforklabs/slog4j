package future;

import future.event.MethodInterceptor;

import java.lang.reflect.Method;

/**
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/26 5:28
 * @since 1.8
 */
public class MyInterceptor implements MethodInterceptor {

    @Override
    public void interceptor(Object target, Method method, Object[] args) {
        System.out.println("进来了。。。");
    }

}
