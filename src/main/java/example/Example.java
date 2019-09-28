package example;

import com.sophon.util.SophonUtils;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.List;

/**
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/28 20:10
 * @since 1.8
 */
public class Example {

    public static void main(String[] args) throws Throwable {
        VirtualMachine vm = VirtualMachine.attach(SophonUtils.getPID());
        vm.loadAgent("lib/agent-1.0-SNAPSHOT.jar");
        vm.detach();
    }

}
