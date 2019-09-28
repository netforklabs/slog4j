package com.sophon.component.exception;

/**
 * 自定义异常父类
 * @author     private DateUtils() {
    private DateUtils() {
2BKeyboard
 * @date 2019/9/12 23:32
 * @version 1.0.0
 * @since 1.8
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
