package com.boar.demo2;

public class Client {
    public static void main(String[] args) {
        UserServiceImp userServiceImp=new UserServiceImp();
        UserServiceProxy proxy=new UserServiceProxy();
        proxy.setUserService(userServiceImp);
        proxy.delete();
    }


}
