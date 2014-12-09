package me.arthinking.code4jvmnote;

import java.lang.reflect.Method;

/** 
 * JavaClass 执行工具 
 * 
 * @author zzm 
 */ 
public class C9_7_JavaClassExecuter{ 
	/** 
	 * 执行 外部传过来的代表一个 Java 类的 byte 数组<br> 
	 * 将 输入类的 byte 数组中代表 java.lang.System 的 CONSTANT_Utf8_info常量修改为劫持后的 HackSystem 类
	 * 执行 方法为该类的 static main（ String[] args） 方法，输出结果为该类向 System.out/
	 * err 输出的信息
	 * @param classByte 代表一个 Java 类的 byte 数组 
	 * @return 执行结果 
	 */
	public static String execute(byte[] classByte){ C9_6_HackSystem.clearBuffer(); 
	C9_4_ClassModifier cm = new C9_4_ClassModifier(classByte); 
	byte[] modiBytes= cm.modifyUTF8Constant(" java/ lang/ System",
			" org/ fenixsoft/ classloading/ execute/ HackSystem"); 
	C9_3_HotSwapClassLoader loader = new C9_3_HotSwapClassLoader(); 
	Class clazz = loader.loadByte(modiBytes); 
	try {
		Method method = clazz.getMethod("main", new Class[]{ String[]. class}); 
		method.invoke(null, new String[]{ null}); 
	}catch(Throwable e){ 
		e.printStackTrace(C9_6_HackSystem.out);
	} 
	return C9_6_HackSystem.getBufferString();
	}
}
