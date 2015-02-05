package me.arthinking.app.datatransform.controller;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

/**
 * 结果处理器接口，根据app接口常用的结果封装，抽象出如下接口
 * @author arthinking
 *
 */
public interface TempResultHandler {

	ResultHandler extract(HttpServletRequest req);
	
	/**
	 * 获取到结果
	 */
	JSONObject process();
	
	/**
	 * 获取到结果集
	 * @return
	 */
	JSONObject getData();
	
	/**
	 * 设置结果集
	 * @param data
	 */
	void setData(JSONObject data);
	
	/**
	 * 获取消息
	 * @return
	 */
	String getMsg();
	
	/**
	 * 设置消息
	 * @param msg
	 */
	void setMsg(String msg);
	
	
}
