package com.boar.demo4;

import com.boar.demo2.UserService;
import com.boar.demo2.UserServiceImp;

public class Client {
    public static void main(String[] args) {
        //真实对象
        UserServiceImp userServiceImp=new UserServiceImp();
        //代理角色,不存在
         ProxyInvocationHandler handler = new ProxyInvocationHandler();
         handler.setTarget(userServiceImp);//设置要代理的对象
        //动态生成代理类
        UserService proxy = (UserService) handler.getProxy();
        proxy.delete();
    }

}
