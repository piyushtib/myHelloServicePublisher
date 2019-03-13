package com.tibrep;

public class HelloServiceInterfaceImpl implements HelloServiceInterface {
    @Override
    public void sayHello(String aName) {
        System.out.println("Hello"+ aName);
    }
}
