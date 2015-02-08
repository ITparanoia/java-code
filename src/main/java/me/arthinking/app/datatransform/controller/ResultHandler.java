package me.arthinking.app.datatransform.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * 结果处理器
 * @author arthinking
 *
 */
public class ResultHandler<T> {

	public ResultHandler(int pageNo, int pageSize, int limit){
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.limit = limit;
	}
	
	public static <T> ResultHandler<T> extract(HttpServletRequest req){
		int pageNo = NumberUtils.toInt(req.getAttribute("pageNo")+"", 0);
		int pageSize = NumberUtils.toInt(req.getAttribute("pageSize")+"", 0);
		int limit = NumberUtils.toInt(req.getAttribute("limit")+"", 0);
		return new ResultHandler<T>(pageNo, pageSize, limit);
	} 
	
	/**
	 * 结果处理器，根据结果枚举类型传入处理策略
	 */
	private Result result = Result.SUCCESS;
	
	private T data = null;
	
	private String msg;
	
	private int pageNo;
	
	private int pageSize;
	
	private int limit;
	
	private int total;
	
	/**
	 * 调用具体的枚举结果处理返回结果
	 */
	public String toString(){
		return result.toString(this);
	}
	
	public JSONObject toJson(){
		return result.toJSONObject(this);
	}
	
	public void setResult(Result result) {
		this.result = result;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getPageNo() {
		return pageNo;
	}


	public int getPageSize() {
		return pageSize;
	}

	public int getLimit() {
		return limit;
	}
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public static void main(String[] args){
		// test code
		HttpServletRequest req = null;
		ResultHandler<JSONObject> handler = ResultHandler.extract(req);
		handler.setResult(Result.SUCCESS);
		handler.setData(new JSONObject());
		handler.setTotal(10);
		handler.toString();
	}
	
}