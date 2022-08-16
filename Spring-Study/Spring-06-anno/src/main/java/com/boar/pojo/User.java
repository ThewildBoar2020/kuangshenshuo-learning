package com.boar.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
//   相当于 <bean id="user" class="com.boar.pojo.User"/>
@Component
@Scope("singleton")
public class User {
    //   相当于<property name="name" value="李健"/>
    public String name ;

    public String getName() {
        return name;
    }
    @Value("李健")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
