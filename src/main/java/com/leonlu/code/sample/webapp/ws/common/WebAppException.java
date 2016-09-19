package com.leonlu.code.sample.webapp.ws.common;

public class WebAppException extends RuntimeException {
	private String errorCode;
	private Integer httpStatus;
	
	public WebAppException(String errorCode) {
		this(errorCode, 500);
	}
	public WebAppException(String errorCode, Integer httpStatus) {
		super(errorCode);
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
	}
	
	public WebAppException(String errorCode, Throwable error) {
		this(errorCode, error, 500);
	}

	public WebAppException(String errorCode, Throwable error, Integer httpStatus) {
		super(errorCode, error);
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Integer getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}

}
