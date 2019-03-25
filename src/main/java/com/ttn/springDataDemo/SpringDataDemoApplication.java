package com.ttn.springDataDemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDataDemoApplication {

	public static void main(String[] args) throws Exception
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("springDataDemo.xml");
		UserDAO userDAO=context.getBean(UserDAO.class);
		System.out.println("------");
		System.out.println("Names: ");
		System.out.println("------");
		userDAO.printNames();
		UserDAO3 userDAO3=context.getBean(UserDAO3.class);

		System.out.println();
		System.out.println("------");
        System.out.println("UserNames:");
        System.out.println("----------");
		userDAO3.printUserNames();

		System.out.println();
		System.out.print("count = ");
        userDAO3.display();

		System.out.println();
		System.out.println("getUserName()= "+userDAO3.getUserName());

		System.out.println();
		System.out.println("QueryForMap output: ");
        userDAO3.QueryForMap();

		System.out.println();
		System.out.println("QueryForList output");
        userDAO3.QueryForList();

		System.out.println();
		System.out.println("QueryMapperResult:");
		System.out.println(userDAO3.QueryMapper("amar"));

		System.out.println();
		System.out.println("number of records in user table: ");
		userDAO3.sessionFactoryHibernate();

		userDAO3.insert();

		System.out.println("printing username in last: ");
		userDAO3.printUserNames();
	}
}
