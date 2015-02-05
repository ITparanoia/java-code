package me.arthinking.app.datatransform.controller;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;


/**
 * 结果处理的枚举，处理各种结果，统一的参数封装，确保接口参数格式的一致性
 * @author arthinking
 *
 */
public enum Result {
	SUCCESS {
		String toString(ResultHandler handler) {
			return this.toJSONObject(handler).toJSONString();
	    }

		JSONObject toJSONObject(ResultHandler handler) {
			JSONObject result = fromHandler(handler);
			result.put("status", ResultStatus.SUCCESS.value());
	    	if(StringUtils.isEmpty(result.getString("msg")))
	    		result.put("msg", "请求成功");
			return result;
		}
	},
	ERROR {
		String toString(ResultHandler handler){
			return this.toJSONObject(handler).toJSONString();
		}

		JSONObject toJSONObject(ResultHandler handler) {
			JSONObject result = new JSONObject();
			result.put("status", ResultStatus.ERROR.value());
	    	if(!StringUtils.isEmpty(handler.getMsg()))
	    		result.put("msg", handler.getMsg());
	    	result.put("msg", "请求错误");
	    	// 结果集
	    	result.put("data", handler.getData());
			return result;
		}
	},
	NOTLOGGIN {
		String toString(ResultHandler handler){
			return this.toJSONObject(handler).toJSONString();
		}

		JSONObject toJSONObject(ResultHandler handler) {
			JSONObject result = new JSONObject();
			result.put("status", ResultStatus.ERROR.value());
	    	if(StringUtils.isEmpty(result.getString("msg")))
	    		result.put("msg", "请先登录");
			return result;
		}
	};
	
	JSONObject fromHandler(ResultHandler handler){
		JSONObject result = new JSONObject();
    	int pageNo = handler.getPageNo();
    	int pageSize = handler.getPageSize();
    	int total = handler.getTotal();
    	int limit = handler.getLimit();
    	int totalPage = 0;
    	if(pageSize > 0){
    		totalPage = (total - 1) / pageSize + 1;
    	}
    	if(limit > 0){
    		result.put("limit", limit);
    	} else {
    		result.put("pageNo", pageNo);
        	result.put("pageSize", pageSize);
        	result.put("totalPage", totalPage);
    	}
    	result.put("data", handler.getData());
    	result.put("msg", handler.getMsg());
		return result;
	}
	
	abstract String toString(ResultHandler handler);
	
	abstract JSONObject toJSONObject(ResultHandler handler);
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