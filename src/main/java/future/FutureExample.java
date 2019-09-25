package future;

import future.anno.ListenerMethod;
import future.anno.TriggerMethod;

/**
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/26 0:10
 * @since 1.8
 */
public class FutureExample {

    public static void  main(String[] args) {
        Test.test("this a",123,true);
        System.out.println("e...");
        System.out.println("a...");
        Test.test("this a",123,true);
    }

}
