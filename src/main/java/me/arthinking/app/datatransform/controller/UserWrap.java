package me.arthinking.app.datatransform.controller;

import java.io.IOException;
import me.arthinking.app.datatransform.bean.User;
import me.arthinking.app.datatransform.vo.UserVo;
import net.sf.json.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class UserWrap {

	public static String toJsonObject() throws JsonGenerationException, JsonMappingException, IOException{
		
	    //create ObjectMapper instance
		
		/*
		// Jackson JSON
	    ObjectMapper objectMapper = new ObjectMapper();
	    Set<String> properties = new HashSet<String>();
	    properties.add("username");
	    FilterProvider filterProvider = new SimpleFilterProvider().addFilter("myFilter",  
	             SimpleBeanPropertyFilter.serializeAllExcept("username", "id"));  
	    objectMapper.setFilters(filterProvider);
	    objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
	    StringWriter stringEmp = new StringWriter();
	    objectMapper.writeValue(stringEmp, user);
	    System.out.println(objectMapper.writeValueAsString(user));
	    */
		
//	     String userJson = Jacksons.me().filter("myFilter", "username").readAsString(user);
//	     System.out.println(userJson);
	    // http://kkrgwbj.iteye.com/blog/2001031
		/*
		User user = new User();
		user.setNickname("Jason");
		UserVo userVo = new UserVo();
		userVo.setUser(user);
	    PropertyFilter filter = new PropertyFilter() {
	        //过滤不需要的字段  
	        public boolean apply(Object source, String name, Object value) {  
	            if("enName".equals(name)|| "username".equals(name)){  
	                return true;  
	            }  
	            return false;  
	        }
	    };
	    */
	    
	    /*
	    SerializeWriter sw = new SerializeWriter();  
	    JSONSerializer serializer = new JSONSerializer(sw);  
	    serializer.getPropertyFilters().add(filter);
	    serializer.getPropertyPreFilters().add(SerializerFeature.WriteMapNullValue);
	    serializer.write(userVo);  
	    System.out.println(sw.toString());  
		*/
		
		User user = new User();
		user.setNickname("Jason");
		UserVo userVo = new UserVo(user);
		System.out.println(JSONObject.fromObject(userVo.toJsonString()));
		return "";
	}
	
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException{
		toJsonObject();
	}
	
}
