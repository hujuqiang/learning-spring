package com.learning;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationTest {

    @Test
    public void contextTest() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        context.start();
        context.getBean(UserService.class);
        //1、加载资源xml
        //2、先创建beanFactory、解析xml文件至beanDefinition并注册到factory当中去
        //3、对beanFactory进行初始化，对Bean（Singleton、非LazyInit、非抽象的bean）进行创建
    }
}
