package com.boar.demo1;

public class Client {
    public static void main(String[] args) {
        //
        Host host=new Host();
        //代理，中介
      Proxy proxy = new Proxy(host);
      proxy.rent();
    }
}
