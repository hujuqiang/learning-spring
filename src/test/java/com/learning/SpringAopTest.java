package com.learning;

import org.junit.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.DefaultAopProxyFactory;
import org.springframework.aop.framework.ProxyFactory;

public class SpringAopTest {

    /**
     * 代理对象创建
     */
    @Test
    public void createProxyTest() {
        Object target = new UserServiceImpl();
        ProxyFactory pf = new ProxyFactory(target);
        CountingBeforeAdvice counting = new CountingBeforeAdvice();
        pf.addAdvice(counting);
        UserService userService = (UserService) pf.getProxy();
        userService.getUser();
    }

    /**
     * 动态移除添加通知
     */
    @Test
    public void createProxyTest2() {
        Object target = new UserServiceImpl();
        ProxyFactory pf = new ProxyFactory(target);
        UserService userService = (UserService) pf.getProxy();
        userService.getUser();

        Advised advised = (Advised) userService;
        CountingBeforeAdvice counting = new CountingBeforeAdvice();
        CountingAfterReturningAdvice counting2 = new CountingAfterReturningAdvice();
        advised.addAdvice(counting);
        advised.addAdvice(counting2);
        userService.getUser();
        advised.removeAdvice(counting2);
        userService.getUser();
    }

    /**
     * AspectJProxyFactory
     */
    @Test
    public void createProxyAspectJByTest() {
        Object target = new UserServiceImpl();
        ProxyFactory pf = new ProxyFactory(target);
        CountingBeforeAdvice counting = new CountingBeforeAdvice();
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression("execution(* *.getUser(..))");
        advisor.setAdvice(counting);
        pf.addAdvisor(advisor);
        UserService userService = (UserService) pf.getProxy();
        userService.getUser();
        userService.setUser();
    }

    @Test
    public void createProxyFactoryTest() {
        Object target = new UserServiceImpl();
        CountingBeforeAdvice counting = new CountingBeforeAdvice();
        DefaultAopProxyFactory factory = new DefaultAopProxyFactory();
        AdvisedSupport config = new AdvisedSupport();
        config.setTarget(target);
        config.addAdvice(counting);
        UserService userService = (UserService) factory.createAopProxy(config);
        userService.getUser();
    }
}
