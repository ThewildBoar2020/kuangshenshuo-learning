package com.boar.MyConfig;


import com.boar.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration//这个也会spring 容器托管，注册到容器中，因为 它本身也是一个@Configuration，
// @Configuration代表一个配置类，和beans.xml一样
@Import(MyConfig2.class)
public class MyConfig {
    //注册一个Bean，就相遇我们之前写的一个Bean的标签
    //这个方法的名字就相当于bean标签的od属性
    //这个方法的返回值，就相当于bean标签中的class属性
    @Bean
    public User getUser(){
        return new User();//就是返回要注入到Bean的对象
    }
}
