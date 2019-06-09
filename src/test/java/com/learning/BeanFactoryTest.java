package com.learning;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class BeanFactoryTest {

    @Test
    /**
     * bean的创建
     */
    public void createBeanTest() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        UserServiceImpl userService = factory.createBean(UserServiceImpl.class);
        System.out.println(userService);
    }

    @Test
    /**
     * bean的存储和获取
     */
    public void beanStoreTest() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerSingleton("userDao", new UserDaoImpl());
        factory.getBean("userDao");
    }

    /**
     * 依赖关系注入
     */
    @Test
    public void dependencyTest() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerSingleton("userDao", new UserDaoImpl());
        UserServiceImpl userService = (UserServiceImpl) factory.createBean(UserServiceImpl.class, AbstractBeanDefinition.AUTOWIRE_BY_TYPE, true);
        System.out.println(userService);
    }

    @Test
    public void getBeanTest() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions("spring.xml");
        UserServiceImpl userService = factory.getBean(UserServiceImpl.class);
        UserDaoImpl userDao = factory.getBean(UserDaoImpl.class);
        Assert.assertNotNull("userService不能为空", userService);
        Assert.assertNotNull("userDao不能为空", userDao);
    }

    @Test
    public void loadBeanDefinitionTest() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions("spring.xml");
    }
}
