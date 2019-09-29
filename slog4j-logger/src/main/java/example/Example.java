package example;

import future.Test;

/**
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/28 20:10
 * @since 1.8
 */
public class Example {

    public static void main(String[] args) throws Throwable {
        Test.test("this is a",12,true);
        System.out.println("=======================");
        Test.test("this is b",21,false);
    }

}
