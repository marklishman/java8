package com.lishman.java8.javascript;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavaScript {

    public static void main(String[] args) throws ScriptException, FileNotFoundException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

        engine.eval("var a=10; print(a*a);");

        engine.eval("var s = 'abc-xyz'; print ('The length is ' + s.length);");

        Reader reader = new FileReader("js/website.js"); 
        String result = (String) engine.eval(reader);            
        System.out.println(result.substring(0, 85));
        
        engine.eval(new FileReader("js/person.js"));
        
    }
}
