package me.arthinking.app.datatransform.vo;

import me.arthinking.app.datatransform.bean.User;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

public class UserVo {

	private static String[] formatterAttribute = 
		{"enName", "username", "id"};
	
	public UserVo(User user){
	}
	
	private String username;
	
	private String enName;
	
	private long id;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String toJsonString(){
		return this.toJsonString(formatterAttribute);
	}
	
	public String toJsonString(String[] attr){
		SimplePropertyPreFilter preFilter = 
				new SimplePropertyPreFilter(this.getClass(), attr);
		return JSON.toJSONString(this, preFilter, SerializerFeature.WriteMapNullValue);
	}
	
}