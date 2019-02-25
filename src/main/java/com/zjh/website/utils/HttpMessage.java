package com.zjh.website.utils;

/**
 * 返回消息类
 * @author zjh
 */
public class HttpMessage {

    protected String httpCode;
    protected String message;

    public HttpMessage(){}

    public HttpMessage(String httpCode) {
        this.httpCode = httpCode;
    }

    public String getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(String httpCode) {
        this.httpCode = httpCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HttpMessage{" +
                "httpCode='" + httpCode + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
