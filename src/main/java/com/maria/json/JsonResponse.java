package com.maria.json;


import java.util.Map;

/**
 *
 */
public class JsonResponse implements WebResponse {


	private Object data;
	private long total;
	private String message;
	private boolean success = true;

	/** 기본필드 외에 추가로 필요할 경우 사용 */
	private Map<String, Object> options;

	public JsonResponse() {
	}

	public JsonResponse(Object data) {
		this.data = data;
	}

	public JsonResponse(Object data, long total) {
		this.data = data;
		this.total = total;
	}

	public JsonResponse(boolean success) {
		this.success = success;
	}

	public JsonResponse(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Map<String, Object> getOptions() {
		return options;
	}

	public void setOptions(Map<String, Object> options) {
		this.options = options;
	}
}
