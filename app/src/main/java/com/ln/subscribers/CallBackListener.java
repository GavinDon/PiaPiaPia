package com.ln.subscribers;

/**
 * Created by linan   on 2016/10/12.
 */
public interface CallBackListener<T> {
    void success(T t);
    void faild(String errorMsg);

}
