package com.ttn.springDataDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
@Repository
public class UserDAO5 {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.MANDATORY,noRollbackFor = RuntimeException.class)
    public void insert() {
        String sql = "INSERT INTO user(username,password,name,age,dob)VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{"DAO5", "4321", "Pooja", 21, new Date()});

        throw new RuntimeException();
    }

}