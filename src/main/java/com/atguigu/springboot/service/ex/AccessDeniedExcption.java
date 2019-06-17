package com.atguigu.springboot.service.ex;

/**
 * 访问异常或用户不正确或没有登录
 */
public class AccessDeniedExcption extends ServiceException {
    public AccessDeniedExcption() {
        super();
    }

    public AccessDeniedExcption(String message) {
        super(message);
    }

    public AccessDeniedExcption(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessDeniedExcption(Throwable cause) {
        super(cause);
    }

    protected AccessDeniedExcption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
