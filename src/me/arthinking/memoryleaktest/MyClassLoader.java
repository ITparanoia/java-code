package me.arthinking.memoryleaktest;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {  
    
    private String name; // ClassLoader name
  
    private String path = "d://dev/workspace/java-code/bin/"; // .class path  
  
    private final String fileType = ".class";
    
    private static MyClassLoader myClassLoader = new MyClassLoader();
  
    public MyClassLoader() {
        super();
    }
    
    public MyClassLoader(String name) {  
        super();  // super is System ClassLoader  
        this.name = name;  
    }
  
    public MyClassLoader(ClassLoader parent, String name) {  
        super(parent);  // assign the parent ClassLoader with param
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
     * read the class file into a byte array 
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
     * JVM call this method to findClass, must override, default has not been implement 
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
        // look up HotSwapURLClassLoader object if has loaded the class
        // different HotSwapURLClassLoader has different cache(do not share)
        clazz = findLoadedClass(name);
        if (clazz != null ) {
            if (resolve){
                resolveClass(clazz);
            }
            // if the class has been modified, reloaded it
            if(isModified){
                myClassLoader = new MyClassLoader();
                clazz = myClassLoader.findClass(name);
            }
            return (clazz);
        }
        */
        // load "java.*" class with AppClassLoader(the system default ClassLoader)
        if(name.startsWith("java.")){
            try {
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