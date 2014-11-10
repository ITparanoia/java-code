package me.arthinking.memoryleaktest;

import java.util.ArrayList;
import java.util.List;


/**
 * VM Args:-verbose:gc -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * 限制Java堆大小为20M，不可扩展，让虚拟机在出现内存溢出异常时Dump出当前的内存堆转储快照
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