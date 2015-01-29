package me.arthinking.app.datatransform.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class UserWrap {

	public static String toJsonObject() throws JsonGenerationException, JsonMappingException, IOException{
		
		User user = new User();
        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();
        Set<String> properties = new HashSet<String>();
        properties.add("username");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("myFilter",  
                SimpleBeanPropertyFilter.serializeAllExcept("username", "id"));  
        objectMapper.setFilters(filterProvider);
        // objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        StringWriter stringEmp = new StringWriter();
        // objectMapper.writeValue(stringEmp, user);
        System.out.println(objectMapper.writeValueAsString(user));
        // String userJson = Jacksons.me().filter("myFilter", "username").readAsString(user);
        // System.out.println(userJson);
		return "";
	}
	
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException{
		toJsonObject();
	}
	
}
