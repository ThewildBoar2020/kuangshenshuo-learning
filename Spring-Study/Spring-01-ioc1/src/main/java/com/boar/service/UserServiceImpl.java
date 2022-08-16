package com.boar.service;

import com.boar.Dao.UserDao;
import com.boar.Dao.UserDaoImpl;

public class UserServiceImpl implements UserService{
    private UserDao userDao;//组合的概念，调用
   //利用set进行动态实现值的注入
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void getUser() {
     userDao.getUser();
    }
}
