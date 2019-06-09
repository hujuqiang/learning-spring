package com.learning;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibTest {

    @Test
    public void cglibTest() {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("前置方法");
                Object result = null;
                try {
                    result = methodProxy.invoke(userServiceImpl, objects);
                } catch (Exception e) {
                    System.out.println("异常通知");
                    e.printStackTrace();
                }
                System.out.println("后置方法");
                return result;
            }
        });
        UserService userService = (UserService) enhancer.create();
        userService.getUser();
    }
}
