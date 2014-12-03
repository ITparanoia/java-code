package me.arthinking.code4jvmnote;

/**
 * 新生代 Minor GC
 * 深入理解Java虚拟机中的代码
 */
public class C_3_5_Minor_GC {

    private static final int _1MB = 1024 * 1024;
    
    public static void main(String[] args) {
        testAllocation();
    }
    
    /**
     * VM参数： -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * Java堆大小为20MB，不可扩展，其中10MB分配给新生代，剩下的10MB分配给老年代。
     * -XX:SurvivorRatio=8表示新生代中Eden区与一个Survivor区的空间比例是8:1
     * 
     * void
     */
    public static void testAllocation(){
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];  // 出现一次Minor GC
    }
    
    /**
     result: 
               分配allocation4时，Eden已经被占用6MB，不够了（总共8MB），所以发生了一次Minor GC
     GC期间发现已有的3x2MB大小对象无法全部放入Survivor空间
               所以只好通过分配担保机制提前转移到老年代去了
     
          
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
     
     GC结束后，4MB的allocation4对象被分配到Eden中，Survivor空闲，老年代占用6MB
 
     */
    
}
