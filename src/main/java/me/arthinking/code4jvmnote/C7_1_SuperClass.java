package me.arthinking.code4jvmnote;


/**
 * 被动使用类字段演示一： 
 * 通过子类应用父类的静态字段，不会导致子类初始化
 * 来自深入理解Java虚拟机的代码
 */

/**
 *  非主动使用类字段演示 
 */
public class C7_1_SuperClass {
    public static void main(String[] args){
        System.out.println(SubClass.value);
        /**
SuperClass init!
123         
         */
    }
}

class SuperClass{
    static{
        System.out.println("SuperClass init!");
    }
    public static int value = 123;
}

class SubClass extends SuperClass{
    static {
        System.out.println("SubClass init!");
    }
}