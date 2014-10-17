package me.arthinking.memoryleaktest;

import java.util.ArrayList;
import java.util.List;

public class MemoryLeak {

    public static void main(String[] args) throws Exception{
        List<Class<?>> list = new ArrayList<Class<?>>();
        Class<?> clazz = null;
        Class<?> clazz2 = null;
        while(true){
            System.out.println("load class...");
            MyClassLoader loader = new MyClassLoader("loader1");  
            clazz = loader.loadClass("me.arthinking.test.MemLeak", false);
            clazz2 = loader.loadClass("me.arthinking.test.MemLeak2", false);
            clazz.newInstance();  // init
            clazz2.newInstance();  // init
            list.add(clazz);
        }
    }
}