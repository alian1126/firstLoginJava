package com.atguigu.springboot.service;

import com.atguigu.springboot.entity.User;
import com.atguigu.springboot.service.ex.InsertException;
import com.atguigu.springboot.service.ex.PasswordNotMatchException;
import com.atguigu.springboot.service.ex.UserNotFoundException;
import com.atguigu.springboot.service.ex.UsernameDuplicateException;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface IUserService {
    void reg(User user, HttpSession session)throws UsernameDuplicateException,InsertException;
    User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException;
    List<User> findAll();
    void delUser(Integer id);
    void updata(Integer id,String tel,String gender);
    List<User> findAllPage(int pageNo, int pageSize);
}
