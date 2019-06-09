package com.learning;

import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class JavaProxyTest {

    @Test
    public void javaProxyTest() {
        final UserServiceImpl userServiceImpl = new UserServiceImpl();
        UserService userService = (UserService) Proxy.newProxyInstance(JavaProxyTest.class.getClassLoader(), new Class[]{UserService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("前置通知");
                try {
                    method.invoke(userServiceImpl, args);
                } catch (Exception e){
                    System.out.println("异常通知");
                    e.printStackTrace();
                }
                System.out.println("后置通知");
                return null;
            }
        });
        userService.getUser();
        // proxy
        // InvocationHandler
    }

    @Test
    public void generatorClass() throws Exception {
        int accessFlags = Modifier.PUBLIC | Modifier.FINAL;
        byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
                "proxy0", new Class[]{UserService.class}, accessFlags);
        Path path = new File(System.getProperty("user.dir") + "/target/proxy0.class").toPath();
        Files.write(path, proxyClassFile, StandardOpenOption.CREATE);
    }
}
