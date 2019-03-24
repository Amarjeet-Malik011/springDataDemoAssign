package com.ttn.springDataDemo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public class UserDAO4 {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserDAO5 userDAO5;
    @Transactional(propagation = Propagation.NESTED, rollbackFor = RuntimeException.class)
    public void insert(){
        String sql="INSERT INTO user(username,password,name,age,dob)VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql,new Object[]{"bkj","321","Vimal",62,new Date()});
        try {
            userDAO5.insert();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        /*throw new RuntimeException("Exception arrised");*/
    }
}