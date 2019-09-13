package com.sophon.component.exception;

/**
 * @Author tiansheng
 * @Date 2019/9/12 23:32
 * @Description TODO
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
