package com.learning;

public class UserServiceImpl implements UserService {

    private UserDaoImpl userDao;

    public UserServiceImpl() {
        System.out.println("创建bean");
    }

    @Override
    public String getUser() {
        return "hjq";
    }

    @Override
    public void setUser() {

    }

    public UserDaoImpl getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }
}
