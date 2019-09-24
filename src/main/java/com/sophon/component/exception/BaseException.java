package com.sophon.component.exception;

/**
 * @author tiansheng
 * @Date 2019/9/12 23:32
 * @Description 自定义异常父类
 */
public class BaseException extends Throwable {

    public BaseException(){}

    public BaseException(String message){
        super(message);
    }

    public BaseException(BaseException e1) throws BaseException {
        throw e1;
    }

}
