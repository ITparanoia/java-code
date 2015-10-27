package me.arthinking.json;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

/**
 * json工具，jackson转换json-lib效率更高，更完善和准确.
 * 
 * 
 */
public class JacksonUtils {

	/**
	 * 共用ObjectMapper，提升JSON转化效率
	 */
	private static ObjectMapper mapper = new ObjectMapper();

	static {
	}
	/**
	 * 获取jackSon ObjectMapper对象
	 * @return
	 */
	public static ObjectMapper getObjectMapper(){
		if (mapper == null) {  
			mapper = new ObjectMapper();  
			// mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
			mapper.setNodeFactory(JsonNodeFactory.withExactBigDecimals(true));
        } 
		return mapper;
	}

	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();

        // here we set BigDecimal as type for floating numbers
        mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);

        MyItem i = new MyItem();
        i.setDesc("Test");
		i.setValue(1.010);

        String s = null;

        try {
            s = mapper.writeValueAsString(i);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("JAVA to JSON: " + s);

        MyItem i2 = null;
        try {
            i2 = mapper.readValue(s, MyItem.class);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nJSON to JAVA: " + i2.getValue());
        System.out.println("\n\"Value\" field type: : " + i2.getValue().getClass());
	}

	/**
	 * List利用json，进行深拷贝.
	 * 
	 * @param src
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> deepListClone(List<Map<String, Object>> src) {
		List<Map<String, Object>> dest = null;

		try {
			String jsonStr = mapper.writeValueAsString(src);
			dest = mapper.readValue(jsonStr, List.class);
		} catch (Exception e) {
		}
		return dest;
	}

	/**
	 * List利用json，进行对象深拷贝.
	 * 
	 * @param src
	 * @return
	 */
	public static <T> List<T> deepListObjectClone(List<T> src, Class<T> clazz) {
		List<T> dest = null;

		try {
			String jsonStr = mapper.writeValueAsString(src);
			dest = mapper.readValue(jsonStr, getCollectionType(src.getClass(), clazz));
		} catch (Exception e) {
		}
		return dest;
	}

	/**
	 * List利用json，序列化.
	 * 
	 * @param src
	 * @return
	 */
	public static String jsonListSerializer(List<Map<String, Object>> src) {
		String jsonStr = null;
		try {
			jsonStr = mapper.writeValueAsString(src);

		} catch (Exception e) {
		}
		return jsonStr;
	}

	/**
	 * List列表转为json字符串.
	 * 
	 * @param src
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String listToJsonStr(List src) {
		String jsonStr = null;
		try {
			jsonStr = mapper.writeValueAsString(src);

		} catch (Exception e) {
		}
		return jsonStr;
	}

	/**
	 * Map转为json字符串.
	 * 
	 * @param src
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String mapToJsonStr(Map src) {
		String jsonStr = null;
		try {
			jsonStr = mapper.writeValueAsString(src);

		} catch (Exception e) {
		}
		return jsonStr;
	}

	/**
	 * List利用json，反序列化.
	 * 
	 * @param src
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> jsonListUnSerializer(String jsonStr) {

		List<Map<String, Object>> dest = null;
		try {

			dest = mapper.readValue(jsonStr, List.class);
		} catch (Exception e) {
		}
		return dest;
	}

	/**
	 * List利用json，指定List元素类型，反序列化.
	 * 
	 * @param src
	 * @return
	 */
	public static <T> List<T> jsonListUnSerializer(String jsonStr, Class<T> elementType) {

		List<T> dest = null;
		try {
			dest = mapper.readValue(jsonStr, getCollectionType(List.class, elementType));
		} catch (Exception e) {
		}
		return dest;
	}

	/**
	 * 获取泛型的Collection Type
	 * 
	 * @param collectionClass
	 *            泛型的Collection
	 * @param elementClasses
	 *            元素类
	 * @return JavaType Java类型
	 * @since 1.0
	 */
	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

	/**
	 * Map利用json，序列化.
	 * 
	 * @param src
	 * @return
	 */
	public static String jsonMapSerializer(Map<String, String> src) {
		String jsonStr = null;
		try {
			jsonStr = mapper.writeValueAsString(src);

		} catch (Exception e) {
		}
		return jsonStr;
	}

	/**
	 * Map利用json，反序列化.
	 * 
	 * @param src
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonMapUnSerializer(String jsonStr) {

		Map<String, Object> dest = null;
		try {

			dest = mapper.readValue(jsonStr, Map.class);
		} catch (Exception e) {
		}
		return dest;
	}

	/**
	 * OBJECT利用json，反序列化.
	 * 
	 * @param src
	 * @return
	 */
	public static <T> T jsonObjUnSerializer(String jsonString, Class<T> type) {

		try {
			return mapper.readValue(jsonString, type);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * OBJECT利用json，指定类型引用TypeReference类型反序列化.
	 * 
	 * <pre>
	 * eg：
	 * List<A> lista3 = jsonObjUnSerializer(listj, new TypeReference<List<A>>() {});
	 * A a = jsonObjUnSerializer(listj, new TypeReference<A>() {});
	 * </pre>
	 * 
	 * @param jsonString
	 * @param typeReference
	 * @return
	 */
	public static <T> T jsonObjUnSerializer(String jsonString, TypeReference<T> typeReference) {

		try {
			return mapper.readValue(jsonString, typeReference);
		} catch (Exception e) {
			return null;
		}
	}


	/**
	 * 将对象转换为json字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String jsonObjectSerializer(Object obj) {
		String jsonStr = null;
		try {
			jsonStr = mapper.writeValueAsString(obj);

		} catch (Exception e) {
		}
		return jsonStr;
	}

	/**
	 * 将json字符串转换为对象
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static Object jsonObject(String jsonStr) {
		Object dest = null;
		try {

			dest = mapper.readValue(jsonStr, Object.class);
		} catch (Exception e) {
		}
		return dest;
	}

}

class MyItem {

	private String desc;
	private Number value;

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Number getValue() {
		return this.value;
	}

	public void setValue(Number value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.desc + "= " + this.value;
	}
}