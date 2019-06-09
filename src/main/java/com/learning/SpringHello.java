package com.learning;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHello {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        context.start();
        UserService userService = context.getBean(UserService.class);
        System.out.println(userService);
    }
}
