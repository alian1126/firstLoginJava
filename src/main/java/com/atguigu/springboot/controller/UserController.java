package com.atguigu.springboot.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.atguigu.springboot.entity.User;
import com.atguigu.springboot.repository.UserRepository;
import com.atguigu.springboot.service.ex.AccessDeniedExcption;
import com.atguigu.springboot.service.impl.UserServiceImpl;
import com.atguigu.springboot.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController

public class UserController extends BaseController{

    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserServiceImpl userService;
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id){
       // User user=userRepository.getOne(id);
       /* Optional<User> user=userRepository.findById(id);
        User user2=user.get();*/

       User user=new User();
       user.setId(id);

        Example<User> example =Example.of(user);

         Optional<User> user2 = userRepository.findOne(example);
        User user3=user2.get();
        return user3;
    }



    @RequestMapping("/reg")
    public ResponseResult<Void> insertUser(User user,HttpSession session){
        //System.out.println(user);
userService.reg(user,session);

return new ResponseResult<>(SUCCESS);
    }


    @RequestMapping("/login")
    public ResponseResult<User> login(@RequestBody User user,HttpSession session){

        String username=user.getUsername();
        String password=user.getPassword();


        User result =userService.login(username,password);
        System.out.println(result);

        session.setAttribute("id",result.getId());
        session.setAttribute("username",result.getUsername());

        result.setPassword(null);
return  new ResponseResult<>(SUCCESS,result);
    }

    @RequestMapping("/show")
    public ResponseResult<List<User>> showUsers(HttpSession session){
        Integer id = null;
        Object idSession=session.getAttribute("id");
        System.out.println("idSession"+idSession);
        if(session.getAttribute("id") !=null){
             id=getIdFromSession(session);
        }
        System.out.println("id:"+id);
        if (id==null){
            throw new AccessDeniedExcption("未登录，请先登录");
        }

        List<User> users=userService.findAll();
        for (User u:users
             ) {
            u.setPassword(null);
        }
        return new ResponseResult<>(SUCCESS,users);
    }

   /* @RequestMapping("/showPage")
    public ResponseResult<List<User>> showUsersPage(HttpSession session){

        Integer id = null;

        if(session.getAttribute("id") !=null){
            id=getIdFromSession(session);
        }

        if (id==null){
            throw new AccessDeniedExcption("未登录，请先登录");
        }

        List<User> users=userService.findAll();
        for (User u:users
                ) {
            u.setPassword(null);
        }
        return new ResponseResult<>(SUCCESS,users);
    }*/

    @RequestMapping("/update")
    public ResponseResult<Void> update(Integer id,String tel,String gender){

        userService.updata(id, tel, gender);

        return new ResponseResult<>(SUCCESS);
    }


    @RequestMapping("/del/{id}")
    public ResponseResult<Void> del(@PathVariable("id") Integer id ){
        userService.delUser(id);

        return new ResponseResult<>(SUCCESS);
    }
    @RequestMapping("/rebody")
    public ResponseResult<Void> rebody(@RequestBody User user){
        System.out.println(user);

        return new ResponseResult<>(SUCCESS);
    }
}
