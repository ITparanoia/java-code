package me.arthinking.test;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {  
    
    private String name; // 类加载器的名字  
  
    private String path = "d://dev/workspace/java-code/bin/"; // 加载类的路径  
  
    private final String fileType = ".class"; // .class文件扩展名  
    
    private static MyClassLoader myClassLoader = new MyClassLoader();
  
    public MyClassLoader() {
        super();
    }
    
    public MyClassLoader(String name) {  
        super();// 让系统加载器成为该类的加载器的父类加载器  
        this.name = name;  
    }
  
    public MyClassLoader(ClassLoader parent, String name) {  
        super(parent); // 显示指定该类加载器的父加载器  
        this.name = name;  
    }
  
    @Override  
    public String toString() {
        return this.name;  
    }  
  
    public String getPath() {  
        return path;  
    }  
  
    public void setPath(String path) {  
        this.path = path;  
    }  
      
    /** 
     * 读取class文件作为二进制流放入到byte数组中去 
     * @param name 
     * @return 
     */  
    private byte[] loadClassData(String name) {  
        InputStream in = null;  
        byte[] data = null;  
        ByteArrayOutputStream baos = null;  
  
        try {  
            name = name.replace(".", "\\");  
            in = new BufferedInputStream(new FileInputStream(new File(path  
                    + name + fileType)));  
            baos = new ByteArrayOutputStream();  
            int ch = 0;  
            while (-1 != (ch = in.read())) {  
                baos.write(ch);  
            }  
            data = baos.toByteArray();  
        } catch (Exception e) {  
  
            e.printStackTrace();  
        } finally {  
            try {  
                in.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            } finally {  
                try {  
                    baos.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return data;  
    }  
      
    /** 
     * JVM调用的加载器的方法 
     */  
    @Override  
    protected Class<?> findClass(String name) throws ClassNotFoundException { 
        byte[] data = this.loadClassData(name);  
        return this.defineClass(name, data, 0, data.length);  
    }
    
    @Override
    public Class<?> loadClass(String name,boolean resolve) throws ClassNotFoundException {
        Class clazz = null;
        /*
        //查看HotSwapURLClassLoader实例缓存下，是否已经加载过class
        //不同的HotSwapURLClassLoader实例是不共享缓存的
        clazz = findLoadedClass(name);
        if (clazz != null ) {
            if (resolve){
                resolveClass(clazz);
            }
            //如果class类被修改过，则重新加载
            myClassLoader = new MyClassLoader();
            clazz = myClassLoader.findClass(name);
            return (clazz);
        }
        */
        //如果类的包名为"java."开始，则有系统默认加载器AppClassLoader加载
        if(name.startsWith("java.")){
            try {
                //得到系统默认的加载cl，即AppClassLoader
                ClassLoader system = ClassLoader.getSystemClassLoader();
                clazz = system.loadClass(name);
                if (clazz != null) {
                    if (resolve)
                        resolveClass(clazz);
                    return (clazz);
                }
            } catch (ClassNotFoundException e) {
                // Ignore
            }
        }
        return this.findClass(name);
    }
      
    public static void main(String[] args) throws Exception {  
        MyClassLoader loader1 = new MyClassLoader("loader1");  
        loader1.setPath("d://dev/workspace/java-code/bin/");  
        test(loader1);  
    }  
      
    public static void test(ClassLoader loader) throws Exception{  
        Class<?> clazz = loader.loadClass("me.arthinking.test.MemLeak");
        System.out.println(clazz.getName());
    }  
  
}  