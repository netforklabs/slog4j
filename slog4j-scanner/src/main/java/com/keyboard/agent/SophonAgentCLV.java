package com.keyboard.agent;

import java.lang.instrument.Instrumentation;

/**
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/29 6:27
 * @since 1.8
 */
public class SophonAgentCLV {

    public static void agentmain(String agentArgs, Instrumentation inst){
        System.out.println("sb");
    }

}
