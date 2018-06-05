package com.example.fly.baselibrary.net;

import com.example.fly.baselibrary.net.download.ProgressListener;

import java.io.File;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * @author fei.wang
 * @time 2017/7/18
 * @tips 这个类是Object的子类
 * @fuction 上传进度监听
 */
public class UploadFileRequestBody extends RequestBody {

    private RequestBody mRequestBody;
    private ProgressListener mProgressListener;

    private BufferedSink bufferedSink;

    public UploadFileRequestBody(File file, ProgressListener progressListener) {
        this.mRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        this.mProgressListener = progressListener;
    }

    public UploadFileRequestBody(RequestBody requestBody, ProgressListener progressListener) {
        this.mRequestBody = requestBody;
        this.mProgressListener = progressListener;
    }

    @Override
    public MediaType contentType() {
        return mRequestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return mRequestBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        if (bufferedSink == null) {
            //包装
            bufferedSink = Okio.buffer(sink(sink));
        }
        mRequestBody.writeTo(bufferedSink);
        bufferedSink.flush();
    }

    private Sink sink(Sink sink) {
        return new ForwardingSink(sink) {
            //当前写入字节数
            long bytesWritten = 0L;
            //总字节长度，避免多次调用contentLength()方法
            long cotentLength = 0L;

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (contentLength() != 0) {
                    //获取contentLength的值，后续不再使用
                    cotentLength = contentLength();
                }
                //增加当前写入的字节数
                bytesWritten += byteCount;
                //回调
                mProgressListener.onProgressChange(bytesWritten, cotentLength, bytesWritten == cotentLength);
            }
        };
    }
}
