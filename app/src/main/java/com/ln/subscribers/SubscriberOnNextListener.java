package com.ln.subscribers;
/**
 * Created by linan   on 2016/10/12.
 */
public interface SubscriberOnNextListener<T> {
    void onNext(T t);
    void onError(String msg);
}
