package com.atguigu.springboot.repository;

import com.atguigu.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {


    User getByUsername(String userName);



    /*
        * @Modifying
        @Query("UPDATE Person p SET p.email = :email WHERE id = :id")
        void updatePersonEmail(@Param("id") Integer id, @Param("email") String email);

        @Query("SELECT p FROM Person p WHERE p.lastName = ?1 AND p.email = ?2")
        List<Person> testQueryAnnotationParams1(String lastName, String email);
        * */
   /* @Modifying
    @Query("UPDATE User u SET u.tel = ?1 , u.gender = ?2 WHERE u.id = : id")
    void updata(String tel, String gender,Integer id);*/
   @Modifying
    @Query("UPDATE User u SET u.tel = :tel , u.gender = :gender WHERE u.id = :id")
    void updata(@Param("id")Integer id,@Param("tel") String tel,@Param("gender") String gender);
}
