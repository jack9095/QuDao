package com.archie.netlibrary.okhttp.request;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * 文件名:   CommonRequest
 * 描述:     接收请求参数，为我们生成Request对象
 */

public class CommonRequest {

    /**
     * 创建Get请求的Request
     *
     * @param url
     * @param params
     * @param token
     * @param isHead true 表示需要请求头 false 不需要请求头
     * @return 通过传入的参数返回一个Get类型的请求
     */
    public static Request createGetRequest(String url, RequestParams params,String token,boolean isHead) {
        StringBuilder urlBuilder = new StringBuilder(url).append("?");

        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                urlBuilder
                        .append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
        }

        Request.Builder builder = new Request.Builder();
        builder.url(urlBuilder.substring(0, urlBuilder.length() - 1));
        if (isHead) {
            builder.header("Authentication", "Basic" + token.trim());
        }
        return builder.get().build();
    }

    /**
     * 创建Post请求的Request
     *
     * @param url
     * @param params
     * @return 返回一个创建好的Request对象
     */
    public static Request createPostRequest(String url, RequestParams params,String token,boolean isHead) {
        FormBody.Builder mFromBodyBuilder = new FormBody.Builder();

        if (params != null) {
//            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                //将请求参数逐一遍历添加到我们的请求构建类中
                mFromBodyBuilder.add(entry.getKey(), entry.getValue());
            }
        }

        //通过请求构建类的build方法获取到真正的请求体对象
        FormBody mFormBody = mFromBodyBuilder.build();

        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (isHead) {
            builder.header("Authentication", "Basic " + token);
            builder.header("Content-type", "Basic " + token);
        }
        return builder.post(mFormBody).build();
    }

}
