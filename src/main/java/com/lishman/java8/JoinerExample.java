package com.lishman.java8;

import java.util.StringJoiner;

public class JoinerExample {

    public static void main(String[] args) {
        
        String[] a = new String[] {"aaa", "bbb", "ccc"};

        String result = String.join("-", a);
        System.out.println(result);
        
        StringJoiner sj1 = new StringJoiner(",", "{", "}");
        sj1.setEmptyValue("empty");
        System.out.println(sj1);
        
        sj1.add("xxx")
            .add("yyy")
            .add("zzz");
        System.out.println(sj1);

        StringJoiner sj2 = new StringJoiner(" and ");
        Person.getPeopleList().forEach(p -> sj2.add(p.name));
        System.out.println(sj2);
        
    }
    
}
