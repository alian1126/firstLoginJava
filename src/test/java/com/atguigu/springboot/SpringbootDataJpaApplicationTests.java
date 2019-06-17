package com.atguigu.springboot;

import com.atguigu.springboot.entity.User;
import com.atguigu.springboot.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDataJpaApplicationTests {

    @Autowired
    private UserServiceImpl userService;
    @Test
    public void contextLoads() {
        Integer id = 6;
        String tel= "15199999999";
        String gender ="女";
        userService.updata(id,tel,gender);
    }
    @Test
    public void findAllPage() {

       List<User> users= userService.findAllPage(2,3);
        System.out.println(users);
    }
    @Test
    public void test(){

        User user=new User();
        user.setUsername("jiu");
       // userService.reg(user);
    }
    @Test
    public void test2(){


        List<User> users=userService.findAll();
        for (User u:users
             ) {
            System.out.println(u);
        }
    }
}
