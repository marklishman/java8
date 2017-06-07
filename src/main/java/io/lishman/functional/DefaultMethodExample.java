package io.lishman.functional;

// Eliminates the need for the 'Abstract' inheritance layer

public interface DefaultMethodExample {
    
    void interfaceMethod();
    
    default void defaultMethod() {
        interfaceMethod();
    }
    
    static void defaultStaticMethod() {
        System.out.println("A static method in an interface");
    }

}
