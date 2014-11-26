package me.arthinking.code4jvmnote;

/**
 * ������ Minor GC
 * �������Java������еĴ���
 */
public class C_3_5_Minor_GC {

    private static final int _1MB = 1024 * 1024;
    
    public static void main(String[] args) {
        testAllocation();
    }
    
    /**
     * VM������ -verbose:gc-Xms20M-Xmx20M-Xmn10M-XX:+PrintGCDetails-XX:SurvivorRatio=8
     * void
     */
    public static void testAllocation(){
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[2 * _1MB];  // ����һ��Minor GC
    }
    /**
     result:
     
 
     */
    
}
