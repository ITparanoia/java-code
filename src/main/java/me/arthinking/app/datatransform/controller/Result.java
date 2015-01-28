package me.arthinking.app.datatransform.controller;

import com.alibaba.fastjson.JSONObject;


/**
 * 结果处理的枚举，处理各种结果，统一的参数封装，确保接口参数格式的一致性
 * 由于只有一个方法，把结果作为状态枚举，减少了类文件个数
 * @author arthinking
 *
 */
public enum Result {
	SUCCESS {
		JSONObject transform(ResultHandler handler) {
	    	JSONObject result = new JSONObject();
	    	// 结果状态
	    	result.put("status", ResultStatus.SUCCESS.value());
	    	// 结果提示
	    	result.put("msg", handler.getMsg());
	    	// 结果集
	    	result.put("data", handler.getData());
	        return result;
	    }
	},
	ERROR {
		JSONObject transform(ResultHandler handler){
			JSONObject result = new JSONObject();
			// 结果状态
			result.put("status", ResultStatus.ERROR.value());
	    	// 结果提示
	    	result.put("msg", handler.getMsg());
	    	// 结果集
	    	result.put("data", handler.getData());
			return result;
		}
	};
	
	abstract JSONObject transform(ResultHandler handler);
}

enum ResultStatus {
	
	SUCCESS(1), ERROR(-1);
	
	private int result;
	
	ResultStatus(int result){
		this.result = result;
	}
	int value(){
		return this.result;
	}
}