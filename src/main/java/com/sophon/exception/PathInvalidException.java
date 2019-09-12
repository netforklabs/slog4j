package com.sophon.exception;

/**
 * @Author tiansheng
 * @Date 2019/9/13 2:56
 * @Description 标识文件路径无效
 */
public class PathInvalidException extends BaseException{

    public PathInvalidException(){
        super();
    }

    public PathInvalidException(String message){
        super(message);
    }

}
