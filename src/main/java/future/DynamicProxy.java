package future;

import com.sophon.util.SophonUtils;

import java.io.IOException;
import java.lang.instrument.Instrumentation;

/**
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/28 17:55
 * @since 1.8
 */
public class DynamicProxy {

    public static void main(String[] args) {
        System.out.println("system start...");
        System.out.println("system end...");
    }

    public static void agentmain(String agentArgs, Instrumentation instrumentation){
        System.out.println("dynamic proxy");
    }

}
