package com.example.qingchen.vrmr.net;

import com.example.qingchen.vrmr.bean.InfoBean;

import retrofit2.http.GET;
import rx.Observable;


public interface InfoApi {
    /**
     * 获取信息
     * @return
     */
    @GET("/startup/?key=7fed97d2186ea83c78d3e4fd0b58ab56&num=30")
    Observable<InfoBean> getInfo();
}
