package com.learning;

import org.junit.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.framework.DefaultAopProxyFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class AopTest {

    /**
     * 创建一个代理类
     */
    @Test
    public void createProxyTest() {
        DefaultAopProxyFactory factory = new DefaultAopProxyFactory();
        AdvisedSupport config = new AdvisedSupport(UserService.class);
        config.setTarget(new UserServiceImpl());
        CountingBeforeAdvice counting = new CountingBeforeAdvice();
        config.addAdvice(counting);
        AopProxy proxy = factory.createAopProxy(config);
        UserService userService = (UserService) proxy.getProxy();
        userService.getUser();
    }

    /**
     * Advisor 与 ProxyFactory使用与测试
     */
    @Test
    public void createProxyAdvisor() {
        UserServiceImpl target = new UserServiceImpl();
        ProxyFactory pf = new ProxyFactory(target);
        //Advice 拦截通知行为
        CountingBeforeAdvice counting = new CountingBeforeAdvice();
        //连接点与通知的适配器
        Advisor advisor = new DefaultPointcutAdvisor(counting);
        pf.addAdvisors(advisor);
        UserService userService = (UserService) pf.getProxy();
        userService.getUser();
    }

    @Test
    public void createProxyByAspectJ() {
        UserServiceImpl target = new UserServiceImpl();
        ProxyFactory pf = new ProxyFactory(target);
        CountingBeforeAdvice counting = new CountingBeforeAdvice();
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setAdvice(counting);
        advisor.setExpression("execution(* *.getUser(..))");
        pf.addAdvisors(advisor);
        UserService userService = (UserService) pf.getProxy();
        userService.getUser();
    }
}
