package me.arthinking.memoryleaktest;

public class MemLeak{
    public static Byte[] data;  // data store in codesegment
    static {
        System.out.println("init MemLeak");
        data = new Byte[1];
        for(int i=0; i<data.length; i++){
            data[i] = (byte)(i % 2);
        }
    }
}
