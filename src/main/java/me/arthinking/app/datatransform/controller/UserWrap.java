package me.arthinking.app.datatransform.controller;

import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeWriter;

public class UserWrap {

	public static String toJsonObject(){
		
		User user = new User();
        //create ObjectMapper instance
        // ObjectMapper objectMapper = new ObjectMapper();
        Set<String> properties = new HashSet<String>();
        properties.add("username");
        // FilterProvider filterProvider = new SimpleFilterProvider().addFilter("myFilter",  
        //         SimpleBeanPropertyFilter.serializeAllExcept("username", "id"));  
        // objectMapper.setFilters(filterProvider);
        // objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        StringWriter stringEmp = new StringWriter();
        // objectMapper.writeValue(stringEmp, user);
        // System.out.println(objectMapper.writeValueAsString(user));
        // String userJson = Jacksons.me().filter("myFilter", "username").readAsString(user);
        // System.out.println(userJson);
        // http://kkrgwbj.iteye.com/blog/2001031
        // SimplePropertyPreFilter filter = new SimplePropertyPreFilter(User.class, "username");
        PropertyFilter filter = new PropertyFilter() {  
            //过滤不需要的字段  
            public boolean apply(Object source, String name, Object value) {  
                if("username".equals(name)||"id".equals(name)){  
                    return false;  
                }  
                return true;  
            }  
        };
        SerializeWriter sw = new SerializeWriter();  
        JSONSerializer serializer = new JSONSerializer(sw);  
        serializer.getPropertyFilters().add(filter);
        serializer.write(user);  
        System.out.println(sw.toString());  
        // System.out.println(JSON.toJSONString(user, SerializerFeature.WriteMapNullValue));
        // System.out.println(JSON.toJSONString(user, filter, SerializerFeature.WriteMapNullValue));
		return "";
	}
	
	public static void main(String[] args){
		toJsonObject();
	}
	
}
