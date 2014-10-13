package me.arthinking.test;

import java.util.ArrayList;
import java.util.List;

public class MemoryLeak {

    public static void main(String[] args) throws Exception{
        List<Class<?>> list = new ArrayList<Class<?>>();
        while(true){
            System.out.println("load class...");
            MyClassLoader loader = new MyClassLoader("loader1");  
            Class<?> clazz = loader.loadClass("me.arthinking.test.MemLeak", false);
            clazz.newInstance();  // init
            list.add(clazz);
        }
    }
}