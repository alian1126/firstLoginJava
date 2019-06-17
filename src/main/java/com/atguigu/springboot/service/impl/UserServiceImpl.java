package com.atguigu.springboot.service.impl;

import com.atguigu.springboot.entity.User;
import com.atguigu.springboot.repository.UserRepository;
import com.atguigu.springboot.service.IUserService;
import com.atguigu.springboot.service.ex.InsertException;
import com.atguigu.springboot.service.ex.PasswordNotMatchException;
import com.atguigu.springboot.service.ex.UserNotFoundException;
import com.atguigu.springboot.service.ex.UsernameDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public void reg(User user, HttpSession session) throws UsernameDuplicateException, InsertException {
        User result= userRepository.getByUsername(user.getUsername());
        //System.out.println(result);

        if (result==null) {
            User save = userRepository.save(user);
            session.setAttribute("id",save.getId());
            session.setAttribute("username",save.getUsername());

        }else {
            throw new UsernameDuplicateException("尝试注册的用户名"+user.getUsername()+"已经被注册,请换一个用户名");
        }
    }

    @Override
    public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
        User result= userRepository.getByUsername(username);
        if (result==null) {
            // 是：用户名对应的数据不存在，抛出UserNotFoundException
            throw new UserNotFoundException("登录失败,用户名不存在");
        }
        if (!result.getPassword().equals(password)){
            throw new PasswordNotMatchException("登录失败,密码错误!");
        }

        return result;
    }

    @Override
    public List<User> findAll() {
       List<User> users= userRepository.findAll();
        return users;
    }

    @Override
    public void delUser(Integer id) {

        userRepository.deleteById(id);


    }

    @Override
    @Transactional
    public void updata(Integer id, String tel, String gender) {
        userRepository.updata(id, tel, gender);
    }

    @Override
    public List<User> findAllPage(int pageNo, int pageSize) {
       // int pageNo = 0;
       // int pageSize = 5;
        //Pageable 接口通常使用的其 PageRequest 实现类. 其中封装了需要分页的信息
        //排序相关的. Sort 封装了排序的信息
        //Order 是具体针对于某一个属性进行升序还是降序.

        /*//过时
        Order order1 = new Order(Direction.ASC, "id");
        Sort sort = new Sort(order1);*/

        Sort sort = Sort.by(Direction.ASC, "id");
        Pageable pageable =  PageRequest.of(pageNo, pageSize, sort);

       Page<User> users=userRepository.findAll( pageable);
        List<User> usersList= users.getContent();
        return usersList;
    }


}
