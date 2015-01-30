package me.arthinking.app.datatransform.controller;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("myFilter")
public class User {

	private int age;
	
	private String username;
	
	private String nickname;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getId(){
		return "01";
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
>>>>>>> d522888e18105a73067210dc36c0635f55d4dbbe
}