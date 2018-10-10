package com.stream.test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class StreamTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String comp_string = "I love the girl1";
        
        List<String> list = new ArrayList<String>();
        list.add("I am a boy");
        list.add("I love the girl");
        list.add("But the girl loves another girl");
        
        boolean result  = list.stream().anyMatch(item-> {
            System.out.println("Current string is:" + item);
            if(item.equals(comp_string)) {
                return true;
            }
            
            return false;
        });
        
        System.out.println("Compare result: " + result);
        
        ClassLoader cl = StreamTest.class.getClassLoader();
       
        System.out.println("ClassLoader is: " + cl.toString());
        System.out.println("Parent ClassLoader is: " + cl.getParent().toString());
      //  System.out.println("Parent ClassLoader is: " + cl.getParent().getParent().toString());
        
        try {
            
            System.out.println("String classloader is:" + String.class.getClassLoader().toString());
        }catch (Exception e) {
            // TODO: handle exception
            System.out.println("Exception: " + e.getMessage());
        }
        
        DiskClassLoader loader = new DiskClassLoader("F:");
        
        try {
            Class c = loader.findClass("com.frank.test.Test");
            if(c != null) {
                try {
                    Object obj = c.newInstance();
                    Method method = c.getDeclaredMethod("say", null);
                    method.invoke(obj, null);
                 
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
     }

}
