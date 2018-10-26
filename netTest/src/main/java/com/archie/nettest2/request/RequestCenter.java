package com.archie.nettest2.request;

import com.archie.netlibrary.okhttp.CommonOkHttpClient;
import com.archie.netlibrary.okhttp.listener.DisposeDataHandle;
import com.archie.netlibrary.okhttp.listener.DisposeDataListener;
import com.archie.netlibrary.okhttp.request.CommonRequest;
import com.archie.netlibrary.okhttp.request.RequestParams;
import com.archie.nettest2.bean.TestModel;
import com.archie.nettest2.constant.HttpConstant;

import java.io.FileNotFoundException;

/**
 * 项目名:   NetTest2
 * 包名:     com.archie.nettest2.request
 * 文件名:   RequestCenter
 * 创建者:   Jarchie
 * 创建时间: 17/12/13 下午9:50
 * 描述:     统一管理所有的请求
 */

public class RequestCenter {

    private static String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjoiNStMcm1FRTlHTXV1Z2tLaHpsMHdvcmlGY3JyYlB3Z3hvUWRHbjEyKzR4SkMrZVRFNlpERzRQdzlIS1Z4U3Y3ZUpUY2RlUjlIN0FkOWE1ZkdLMGdFd1ZWbDNOL1FTaktKZ1pJUURPZjhsN3RWcmhJNW5iRkRnbnY3WlV3ZEpWa0xRenpRemNVT2lhdENacldRL0JuRkR3c21nRWsrcnRqS3c2LzZ3VGk4V3VaZzM3eTVTeWpiUE1JMjB1bmUyajNQaVVoVGtTam56bmVRSE5IT045bjloa00wUDAveHlqY1FPSVlCTG84bU8rNmk2MndMblY5NUVXSGt5Q0t3SGl5UGNKSUN1N3lGeWlCUGk0dStrdWI4Qk9SSm12UHIvWEl3L3FZdDlLQjVaUXFiQzVvbzVTOEpWWTdLV3VacTkwVkJ2Ym9ML2ViWnI2ZkhlTTVLRUpaOVRFaWJsS3BVY25zK3pWa09lb0NGU3JvWnl2ZlFuclFodllsSitqQW0vblc0MVJ1elpHZWhBbTFCUlc3N3MxZ2I4ZHJOZHB0b1N0TjZEYjdRWHBEbk0rcUJnSHQ4djhJMm1hKzF3NHVzejFmUXVhVlFTT0ZxTDJJWE5LbVB4K09zbXpCczZFOC9wamV0TzRLek04SGF0S2tRQ1NaSnZKblBvUFlKZ3FLZmxvc0J1RCtVQ0ZJdUNuVW83ZHgvZ0QrWXZ0TmR5dXBhM3EwZllTRU5ZcGNsdHN2YlJ2OWJkOGEvTUJqY2RMYXp2WVdpNGhFbHRFelAwcTZKcnhRWGxmZ1dtcUhEZ3VHQy9JNW56aEkzdXd6YTB4UnpuazQvU1ptSFpnYk5oSURrSnlzMkFKMnU1NmZna3d5UTl2cEVkdHJnQzM1bVBKdnFZbStEaDIrRzZENmpRSldDZ0RFVTE4YUdpRlA3N3hXaW1qczNIR0FIWTkvSTI5YWRmVEJrUDUzOGpobjlSQUwxbmNNeHIzQ2xKY1lHcTNqNnJUYjlML1ljMzUycXRsTW52VXdyVUQwN3M0Mlgvbk9MNFBmbHd5ZTdYUzc1RHJRZ3Q3SzJJb1hJWFpycWhsWUdJaWRmOFM0cHVLVU5GV0dZMExlcnIrMHZjUytkRTN2UnhoRnN1KzhHM2owNG00cXhGS2lWUTg0ZXp3TGt3eTg9IiwiZXhwIjoxNTQwNjA0Mzg1LCJqdGkiOiJjY2FlM2M4Yy1lZDAxLTRkNTYtOWQ3NS05ZjgzOTA0NzQ0M2EiLCJpYXQiOjE1NDA1MTc5ODUsImlzcyI6IjEzMDExMSIsInN1YiI6Ij0ifQ.HSXLYhTz0pIJc5f-Zxro2aILtpyQvCvTYhIwlG0_v8E\n";
    //根据参数发送所有的get请求
    private static void getRequest(String url, RequestParams params,
                                   DisposeDataListener listener,
                                   Class<?> clazz){
        CommonOkHttpClient.get(CommonRequest.createGetRequest(url, params,token,true),
                new DisposeDataHandle(listener,clazz));
    }

    //根据参数发送post请求
    private static void postRequest(String url, RequestParams params,
                                   DisposeDataListener listener,
                                   Class<?> clazz){
        CommonOkHttpClient.post(CommonRequest.createGetRequest(url, params,token,true),
                new DisposeDataHandle(listener,clazz));
    }

    public static void requestGetRecommandData(DisposeDataListener listener){
        RequestParams params = new RequestParams();
//        try {
//            params.put("uid",54);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        params.put("projectid","1");
        RequestCenter.getRequest(HttpConstant.HOME_RECOMMAND,params,listener, null);
    }

    public static void requestPostRecommandData(DisposeDataListener listener){
        RequestParams params = new RequestParams();
        params.put("username","北京");
        RequestCenter.postRequest(HttpConstant.HOME_RECOMMAND,params,listener, TestModel.class);
    }

}
