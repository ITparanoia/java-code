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
     * VM������ -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * Java�Ѵ�СΪ20MB��������չ������10MB�������������ʣ�µ�10MB������������
     * -XX:SurvivorRatio=8��ʾ��������Eden����һ��Survivor���Ŀռ������8:1
     * 
     * void
     */
    public static void testAllocation(){
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];  // ����һ��Minor GC
    }
    
    /**
     result: 
               ����allocation4ʱ��Eden�Ѿ���ռ��6MB�������ˣ��ܹ�8MB�������Է�����һ��Minor GC
     GC�ڼ䷢�����е�3X2MB��С�����޷�ȫ������Survivor�ռ�
               ����ֻ��ͨ�����䵣��������ǰת�Ƶ������ȥ��
     
          
[GC [DefNew: 6487K->156K(9216K), 0.0043740 secs] 6487K->6300K(19456K), 0.0044227 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap
 def new generation   total 9216K, used 4580K [0x315e0000, 0x31fe0000, 0x31fe0000)
  eden space 8192K,  54% used [0x315e0000, 0x31a31fa8, 0x31de0000)
  from space 1024K,  15% used [0x31ee0000, 0x31f07138, 0x31fe0000)
  to   space 1024K,   0% used [0x31de0000, 0x31de0000, 0x31ee0000)
 tenured generation   total 10240K, used 6144K [0x31fe0000, 0x329e0000, 0x329e0000)
   the space 10240K,  60% used [0x31fe0000, 0x325e0030, 0x325e0200, 0x329e0000)
 compacting perm gen  total 12288K, used 371K [0x329e0000, 0x335e0000, 0x369e0000)
   the space 12288K,   3% used [0x329e0000, 0x32a3ced8, 0x32a3d000, 0x335e0000)
    ro space 10240K,  54% used [0x369e0000, 0x36f5eb78, 0x36f5ec00, 0x373e0000)
    rw space 12288K,  55% used [0x373e0000, 0x37a849c8, 0x37a84a00, 0x37fe0000)     
     
     GC������4MB��allocation4���󱻷��䵽Eden�У�Survivor���У������ռ��6MB
 
     */
    
}
