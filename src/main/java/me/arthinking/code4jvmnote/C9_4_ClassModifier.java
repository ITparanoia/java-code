package me.arthinking.code4jvmnote;

/** 
 * 修改 Class 文件，暂时只提供修改常量池常量的功能 
 * 将常量池中指定内容的CONSTANT_Utf8_info常量替换为新的字符串
 * 经过ClassModifier处理后的byte[]数组才会传给HotSwapClassLoader.loadByte()方法进行类加载
 * @author zzm 
 */ 
public class C9_4_ClassModifier{ 
	/** 
	 * Class 文件中常量池的起始偏移 
	 */ 
	private static final int CONSTANT_POOL_COUNT_INDEX = 8; 
	/**
     * CONSTANT_ Utf8_ info 常量的 tag 标志 
     */ 
	private static final int CONSTANT_Utf8_info = 1; 
	/**
     * 常量 池中 11 种常量所占的长度， CONSTANT_Utf8_info型常量除外，因为它不是定长的 
     */ 
	private static final int[] CONSTANT_ITEM_LENGTH = 
		{ -1, -1, -1, 5, 5, 9, 9, 3, 3, 5, 5, 5, 5};
	
	private static final int u1 = 1; 
	
	private static final int u2 = 2; 
	
	private byte[] classByte; 
	
	public C9_4_ClassModifier(byte[] classByte){ 
		this. classByte= classByte;
	} 
	
	/** 
	 * 修改常量池中 CONSTANT_Utf8_info 常量的内容
	 * @param oldStr 修改前的字符串 
	 * @param newStr 修改后的字符串 
	 * @return 修改结果 
	 */ 
	public byte[] modifyUTF8Constant(String oldStr, String newStr){ 
		int cpc = getConstantPoolCount(); 
		int offset = CONSTANT_POOL_COUNT_INDEX + u2; 
		for(int i = 0; i < cpc; i++){ 
			int tag = C9_5_ByteUtils.bytes2Int(classByte, offset, u1);
			if(tag == CONSTANT_Utf8_info){ 
				int len = C9_5_ByteUtils.bytes2Int(classByte, offset + u1, u2); 
				offset += (u1 + u2);
				String str = C9_5_ByteUtils.bytes2String(classByte, offset, len);
				if(str.equalsIgnoreCase(oldStr)){ 
					byte[] strBytes = C9_5_ByteUtils.string2Bytes(newStr); 
					byte[] strLen = C9_5_ByteUtils.int2Bytes(newStr.length(), u2);
					classByte = C9_5_ByteUtils.bytesReplace(classByte, offset -u2, u2, strLen);
					classByte = C9_5_ByteUtils.bytesReplace(classByte, offset, len, strBytes); 
					return classByte; 
				}else{ 
					offset += len; 
				} 
			} else { 
				offset += CONSTANT_ITEM_LENGTH[tag]; 
			} 
		} 
		return classByte;
	} 
	/** 
	 * 获取 常量池中常量的数量 
	 * @return 常量池数量 
	 */ 
	public int getConstantPoolCount(){ 
		return C9_5_ByteUtils.bytes2Int(classByte, CONSTANT_POOL_COUNT_INDEX, u2); 
	} 
}