package com.atguigu.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.web.JsonPath;

import javax.persistence.*;

@Entity
@Table(name="tb3_user")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    private  Integer id;
    @Column
    private  String username;
    @Column
    private String password;
    @Column
    private String tel;
    @Column
    private String gender;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
