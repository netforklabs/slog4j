import com.sophon.util.TimeCalc;

import java.io.FileNotFoundException;

/**
 * @Author tiansheng
 * @Date 2019/9/15 22:35
 * @Description TODO
 */
public class Example2 {

    public static String template = "${datetime} ${class} | ${method}:${line} - [${level}]: ";

    public static void main(String[] args) throws FileNotFoundException {
        TimeCalc.invoke(Example2.class,"test1");
    }

    public void test1(){
        for(int i=0; i<1000000; i++){
            StringBuilder sb = new StringBuilder(template);
            sb.replace(0,11,"9102-08-30");
        }
    }

    public void test2(){
        for(int i=0; i<1000000; i++){
            template.trim().replace("${datetime}","9102-08-30");
        }
    }

}
