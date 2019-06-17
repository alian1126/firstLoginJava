package com.atguigu.springboot.controller;

import com.atguigu.springboot.controller.ex.FileUploadException;
import com.atguigu.springboot.service.ex.*;
import com.atguigu.springboot.utils.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;


public abstract class BaseController {
    protected static final int SUCCESS=200;

    @ExceptionHandler({ServiceException.class,FileUploadException.class})
    public ResponseResult<Void> handleException(Throwable e){
        ResponseResult<Void> rr=new ResponseResult<> ();
        rr.setMessage(e.getMessage());

        if (e instanceof UsernameDuplicateException) {
            // 400-用户名冲突异常
            rr.setState(400);
        }else if (e instanceof UserNotFoundException) {
            //401尝试访问的用户数据不存在
            rr.setState(401);
        }else if (e instanceof PasswordNotMatchException) {
            //402验证密码失败,密码错误
            rr.setState(402);
        }else if (e instanceof AccessDeniedExcption) {
            //404 非法访问异常
            rr.setState(404);
        }else if (e instanceof InsertException) {
            // 500-插入数据异常
            rr.setState(500);
        }

        return rr;
    }

    protected final Integer getIdFromSession(HttpSession session) {

        return Integer.valueOf( session.getAttribute("id").toString());
    }
}
