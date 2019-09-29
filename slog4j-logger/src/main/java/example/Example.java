package example;

import com.keyboard.agent.ClassVo;
import com.keyboard.agent.RestoreClassTransformer;
import com.sophon.logger.source.Logger;
import com.sophon.util.SophonUtils;
import com.sun.tools.attach.VirtualMachine;
import future.Test;
import future.ctclass.FutureEvent;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

/**
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/28 20:10
 * @since 1.8
 */
public class Example {

    public static void main(String[] args) throws Throwable {
        VirtualMachine vm = VirtualMachine.attach(SophonUtils.getPID());
        vm.loadAgent("lib/slog4j-scanner-1.0.0.CLV.jar");
        vm.detach();
        Test.test("this is a",12,true);
    }

}
