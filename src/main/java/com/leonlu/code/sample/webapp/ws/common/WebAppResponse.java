package com.leonlu.code.sample.webapp.ws.common;

import java.util.HashMap;
import java.util.Map;

import com.leonlu.code.sample.webapp.ws.util.JacksonUtil;

public class WebAppResponse {
	private Map<String, Object> resultMap = new HashMap<String, Object>();
	
	public WebAppResponse() {
		resultMap.put("success", true);
		resultMap.put("message", null);
	}

	public WebAppResponse(boolean success, String message) {
		resultMap.put("success", success);
		resultMap.put("message", message);
	}

	public void put(String key, Object value) {
		resultMap.put(key, value);
	}
	
	@Override
	public String toString() {
		return JacksonUtil.toJson(resultMap);
	}
	
}
