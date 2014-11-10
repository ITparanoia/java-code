package me.arthinking.memoryleaktest;

import java.util.ArrayList;
import java.util.List;


/**
 * VM Args:-verbose:gc -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * ����Java�Ѵ�СΪ20M��������չ����������ڳ����ڴ�����쳣ʱDump����ǰ���ڴ��ת������
 * @Author zzm
 * Demo for OutOfMemoryError
 */
public class HeapOOM{
    static class OOMObject{
    }
    public static void main(String[] args){
        List<OOMObject> list = new ArrayList<OOMObject>();
        while(true){
            list.add(new OOMObject());
        }
    }
}