package com.sophon.component.exception;

/**
 * 标识文件路径无效
 * @author tiansheng
 * @date 2019/9/13 2:56
 * @version 1.0.0
 * @since 1.8
 */
public class PathInvalidException extends BaseException{

    public PathInvalidException(){
        super();
    }

    public PathInvalidException(String message){
        super(message);
    }

}
