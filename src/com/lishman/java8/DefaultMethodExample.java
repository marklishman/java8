package com.lishman.java8;

public interface DefaultMethodExample {
    
    public void interfaceMethod();
    
    default public void defaultMethod() {
        interfaceMethod();
    }
    
    static void defaultStaticMethod() {
        System.out.println("A static method in an interface");
    }

}
