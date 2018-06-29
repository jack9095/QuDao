package com.example.lttechdemo.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/6/29.
 * 模拟数据（自定义动画）
 */
public class StickModel implements Serializable{
    private String header;
    private String content;
    private String time;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
