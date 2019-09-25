package future;

import future.anno.ListenerMethod;

/**
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/26 3:36
 * @since 1.8
 */
public class Test {

    @ListenerMethod(process = MyInterceptor.class)
    public static void test(String a, int b, boolean c) {
        System.out.println(a + "," + b + "," + c);
    }

}
