package me.arthinking.code4jvmnote;

/** 
 * 为了 多次载入执行类而加入的加载器<br> 
 * 把 defineClass 方法开放出来，只有外部显式调用的时候才会使用到 loadByte 方法 
 * 由 虚拟机调用时，仍然按照原有的双亲委派规则使用 loadClass 方法进行类加载 
 * 
 * @author zzm 
 */ 
public class C9_3_HotSwapClassLoader extends ClassLoader { 
	public C9_3_HotSwapClassLoader(){ 
		// 构造函数中指定为加载 HotSwapClassLoader 类的类加载器作为父类加载器，
		// 这一步是实现提交的执行代码可以访问服务端引用类库的关键。
		super(C9_3_HotSwapClassLoader.class.getClassLoader());
	} 
	
	public Class loadByte(byte[] classByte){ 
		return defineClass(null, classByte, 0, classByte.length);
	}
}
