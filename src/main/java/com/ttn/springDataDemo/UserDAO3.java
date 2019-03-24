package com.ttn.springDataDemo;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


@Repository
public class UserDAO3 {

    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    UserDAO4 userDAO4;

    void print() throws SQLException{
        Connection connection=dataSource.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM  user");
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString("username"));
        }
    }


    void display(){
        String sql="SELECT COUNT(*) FROM user";
        System.out.println(jdbcTemplate.queryForObject(sql,Integer.class));
    }

    String getUserName(){
        String sql="SELECT name FROM user WHERE username = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{"pkj"},String.class);
    }

    void QueryForMap(){
        String sql="SELECT * FROM user WHERE username=?";
        System.out.println( jdbcTemplate.queryForMap(sql,new Object[]{"pkj"}));
    }

    void QueryForList(){
        String sql="SELECT * FROM user";
        System.out.println(jdbcTemplate.queryForList(sql));
    }

    User QueryMapper(String username){
        String sql="SELECT * FROM user WHERE username = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{username},new UserMapper());
    }

    void insertUser(User user) {
        String sql = "INSERT INTO user (username,password,name,age,dob)VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{
                user.getUsername(), user.getPassword(), user.getName(), user.getAge(), user.getDob()
        });
    }

    void sessionFactoryHibernate(){
        String sql="SELECT COUNT(*) FROM Person";
        Query query= sessionFactory.openSession().createQuery(sql);
        System.out.println("COUNT="+((org.hibernate.query.Query) query).uniqueResult());

    }



    @Transactional(propagation = Propagation.REQUIRED, timeout = 2,readOnly = false)
    public void insert(){
        try {
            Thread.sleep(2000L);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }


        String sql="INSERT INTO user(username,password,name,age,dob)VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql,new Object[]{"tkj","123","Tarun",26,new Date()});

        try {
            userDAO4.insert();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }

}
