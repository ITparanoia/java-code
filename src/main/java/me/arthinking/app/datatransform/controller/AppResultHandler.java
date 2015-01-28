package me.arthinking.app.datatransform.controller;

import com.alibaba.fastjson.JSONObject;

/**
 * 结果处理器
 * @author arthinking
 *
 */
public class AppResultHandler implements ResultHandler{

	/**
	 * 结果处理器，根据结果枚举类型传入处理策略
	 */
	private Result result;
	
	private JSONObject data = new JSONObject();
	
	private String msg;
	
	/**
	 * 调用具体的枚举结果处理返回结果
	 */
	@Override
	public JSONObject process(){
		return result.transform(this);
	}

	@Override
	public JSONObject getData() {
		return data;
	}

	@Override
	public void setData(JSONObject data) {
		this.data = data;
	}

	@Override
	public String getMsg() {
		return msg;
	}

	@Override
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}