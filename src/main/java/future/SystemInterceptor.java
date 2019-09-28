package future;

import future.event.MethodInterceptor;

import java.lang.reflect.Method;

/**
 * @author 2BKeyboard
 * @version 1.0.0
 * @date 2019/9/26 6:15
 * @since 1.8
 */
public class SystemInterceptor implements MethodInterceptor {

    @Override
    public void before(Object target, Method method, Object[] args) {
        System.out.println("方法开始");
    }

    @Override
    public void after(Object target, Method method, Object[] args) {
        System.out.println("方法结束");
    }
}
