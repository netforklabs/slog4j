package future.event;

import java.lang.reflect.Method;

/**
 *
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/25 23:59
 * @since 1.8
 */
public interface MethodInterceptor {

    void interceptor(Object target, Method method, Object[] args);

}
