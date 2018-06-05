package com.example.fly.baselibrary.mvpEeventBus;


/**
 * Created by fei.wang on 2017/7/4.
 *
 */
public class EventCenter<T> {
    private T data;
    private String eventCode;

    public EventCenter(String eventCode) {
        this.eventCode = eventCode;
    }

    public EventCenter(String eventCode, T data) {
        this.eventCode = eventCode;
        this.data = data;
    }

    public String getEventCode() {
        return this.eventCode;
    }

    public T getData() {
        return this.data;
    }
}
