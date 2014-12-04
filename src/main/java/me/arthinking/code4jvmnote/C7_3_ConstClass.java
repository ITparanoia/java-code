package me.arthinking.code4jvmnote;

/**
 * 被动使用类字段演示三： 
 * 常量在编译阶段会存入调用类的常量池中，本质上并没有应用到定义类常量的类，<br>
 * 因此不会触发定义常量的类的初始化。
 * 来自深入理解Java虚拟机的代码
 */
public class C7_3_ConstClass {
    public static void main(String[] args){
        System.out.println(ConstClass.HELLOWORLD);
    }
}

class ConstClass{
    static{
        System.out.println("ConstClass init!");
    }
    
    public static final String HELLOWORLD = "hello world";
}
