package com.boar.demo4;

import com.boar.demo3.Rent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//自动生成代理类
public class ProxyInvocationHandler implements InvocationHandler {
    //被代理的接口
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    //生成得到代理类
    public Object getProxy(){
       return Proxy.newProxyInstance(this.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }
    @Override
    //处理代理实例，并返回结果
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
         //动态代理的本质，就是使用反射机制实现
//        seeHouse();
        log(method.getName());
        Object result = method.invoke(target, args);
//        fare();
        return result;
    }
//    public void seeHouse(){
//        System.out.println("中介带看房子");
//    }
//    public void fare(){
//        System.out.println("收中介费");
//    }
    public void log(String msg){
        System.out.println("执行力"+msg+"方法");
    }
}
