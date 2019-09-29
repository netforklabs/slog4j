package future;

import com.sophon.component.hot.SophonListener;

import java.lang.reflect.Method;

/**
 * @author 2BKeyboard
 * @version 1.0.0
 * @date 2019/9/26 6:15
 * @since 1.8
 */
public class SystemInterceptor implements SophonListener {

    @Override
    public void before(Class<?> target, String method, Object[] args) {
        System.out.println("方法开始");
    }

    @Override
    public void after(Class<?> target, String method, Object[] args) {
        System.out.println("方法结束");
    }
}
