package me.arthinking.memoryleaktest;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args:-XX:PermSize=10M -XX:MaxPermSize=10M
 * @author zzm 
 * Demo for RuntimeConstantPool
 */
public class RuntimeConstantPoolOOM {

    public static void test(){
        // ʹ��List�����ų��������ã�����Full GC���ճ�������Ϊ
        List<String> list = new ArrayList<String>();
        // 10MB��PermSize��integer��Χ���㹻����OOM��
        int i=0;
        while(true){
            list.add(String.valueOf(i++).intern());
        }
        /*
        Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
            at java.lang.String.intern(Native Method)
            at me.arthinking.memoryleaktest.RuntimeConstantPoolOOM.test(RuntimeConstantPoolOOM.java:19)
            at me.arthinking.memoryleaktest.RuntimeConstantPoolOOM.main(RuntimeConstantPoolOOM.java:24)
         */
    }
    
    public static void main(String[] args) {
        test();
        /*
        String str1 = new StringBuilder("���").append("����").toString();
        System.out.println(str1.intern() == str1);
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
        */
    }
}
