package com.sophon;

import com.sophon.component.anno.Alone;
import com.sophon.component.logger.LoggerFactory;
import com.sophon.logger.Logger;
import com.sophon.logger.SophonLogger;

/**
 * @Author tiansheng
 * @Date 2019/9/11 15:11
 * @Description TODO
 */
@Alone("/test3/test3.log")
public class Test3 {

    private static final SophonLogger LOGGER = LoggerFactory.getLogger(Test3.class);

    public static void test(){
        for(int i=0; i<5000; i++){
            LOGGER.debug("count:{}",i);
        }
    }

}
