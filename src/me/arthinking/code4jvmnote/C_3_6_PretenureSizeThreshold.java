package me.arthinking.code4jvmnote;

public class C_3_6_PretenureSizeThreshold {

	private static final int _1MB = 1024 * 1024;
	
	/**
	 * VM参数： -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
	 * 深入理解Java虚拟机中的代码
	 */
	public static void testPretenureSizeThreshold(){
		byte[] allocation;
		allocation = new byte[4 * _1MB];  // 直接分配在老年代中
	}
	
	public static void main(String[] args){
		testPretenureSizeThreshold();
	}
	
	/**
	 运行结果：
Heap
 def new generation   total 9216K, used 507K [0x044f0000, 0x04ef0000, 0x04ef0000)
  eden space 8192K,   6% used [0x044f0000, 0x0456ef50, 0x04cf0000)
  from space 1024K,   0% used [0x04cf0000, 0x04cf0000, 0x04df0000)
  to   space 1024K,   0% used [0x04df0000, 0x04df0000, 0x04ef0000)
 tenured generation   total 10240K, used 4096K [0x04ef0000, 0x058f0000, 0x058f0000)
   the space 10240K,  40% used [0x04ef0000, 0x052f0010, 0x052f0200, 0x058f0000)
 compacting perm gen  total 12288K, used 2098K [0x058f0000, 0x064f0000, 0x098f0000)
   the space 12288K,  17% used [0x058f0000, 0x05afca28, 0x05afcc00, 0x064f0000)
No shared spaces configured.
	 */
}
