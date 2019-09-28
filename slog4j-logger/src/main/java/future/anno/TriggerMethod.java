package future.anno;

/**
 * Event事件枚举类，表示在什么情况下触发事件
 * @author 2BKeyboard
 * @version 1.0.0
 * @date 2019/9/26 0:05
 * @since 1.8
 */
public enum TriggerMethod {

    /**
     * 当方法被调用的时候
     */
    DEBUG,

    /**
     * 当方法报错的时候
     */
    ERROR;

}
