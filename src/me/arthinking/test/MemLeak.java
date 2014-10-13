package me.arthinking.test;

public class MemLeak{
    public static Byte[] data;
    static {
        System.out.println("init");
        data = new Byte[1];
        for(int i=0; i<data.length; i++){
            data[i] = (byte)(i % 2);
        }
    }
}
