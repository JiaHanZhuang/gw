package com.zjh.website.utils;

/**\
 *  返回消息拓展类
 * @param <T> 包含的类
 * @author zjh
 */
public class HttpMessageAndObject<T> extends HttpMessage {

    private T obj;

    public HttpMessageAndObject(String code){
        super(code);
    }

    public void setObj(T obj){
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }
}
