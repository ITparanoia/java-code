package me.arthinking.code4jvmnote;


/**
 * ����ʹ�����ֶ���ʾһ�� 
 * ͨ������Ӧ�ø���ľ�̬�ֶΣ����ᵼ�������ʼ��
 */

/**
 *  ������ʹ�����ֶ���ʾ 
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