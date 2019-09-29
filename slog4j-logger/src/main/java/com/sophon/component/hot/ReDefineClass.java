package com.sophon.component.hot;

import com.keyboard.agent.HotModify;
import com.keyboard.register.ListenerMethodEntity;

import java.util.List;

/**
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/30 2:48
 * @since 1.8
 */
public class ReDefineClass {

    public ReDefineClass(){

    }

    public void replacement(List<ListenerMethodEntity> entities){

        HotModify.redefine(null,null);

    }

}
