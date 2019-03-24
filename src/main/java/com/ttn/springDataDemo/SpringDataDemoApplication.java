package com.ttn.springDataDemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDataDemoApplication {

	public static void main(String[] args) throws Exception
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("springDataDemo.xml");
		UserDAO userDAO=context.getBean(UserDAO.class);
		userDAO.printUserNames();
		UserDAO3 userDAO3=context.getBean(UserDAO3.class);
        userDAO3.print();
        userDAO3.display();
        System.out.println(userDAO3.getUserName());
        userDAO3.QueryForMap();
        userDAO3.QueryForList();
        System.out.println(userDAO3.QueryMapper("raj"));
        userDAO3.sessionFactoryHibernate();
		userDAO3.insert();

	}
}
