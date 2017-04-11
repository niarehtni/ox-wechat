package com.oxchains.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 上传图片工具类
 * 
 * @author liuruichao
 * Created on 2016-01-15 16:19
 */
public final class UploadImageUtils {
    public static String uploadImage(File[] files) throws IOException {
        if (files == null || files.length <= 0) {
            throw new IllegalArgumentException("files 不能为空.");
        }
        String resultStr = null;
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://img.51universe.com/upload/img");
        StringBody descBody = new StringBody("desc.", ContentType.MULTIPART_FORM_DATA);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        for (int i = 0; i < files.length; i++) {
            FileBody fileBody = new FileBody(files[i]);
            builder.addPart("file" + i, fileBody);
        }

        builder.addPart("desc", descBody);
        httpPost.setEntity(builder.build());
        HttpResponse response = httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            resultStr = streamToStr(entity.getContent());
        }
        return resultStr;
    }

    public static String uploadImage(List<byte[]> fileDatas, String[] fileNames) throws IOException {
        if (fileDatas == null || fileDatas.size() <= 0) {
            throw new IllegalArgumentException("fileDatas 不能为空.");
        }
        if (fileNames == null || fileNames.length <= 0) {
            throw new IllegalArgumentException("fileNames 不能为空.");
        }
        if (fileDatas.size() != fileNames.length) {
            throw new RuntimeException("上传文件数量与文件名称数量不一致.fileDatas : " + fileDatas.size() + ", fileNames : " + fileNames + ".");
        }
        String resultStr = null;
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://img.51universe.com/upload/img");
        StringBody descBody = new StringBody("desc.", ContentType.MULTIPART_FORM_DATA);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        for (int i = 0; i < fileDatas.size(); i++) {
            ByteArrayBody fileBody = new ByteArrayBody(fileDatas.get(i), fileNames[i]);
            builder.addPart("file" + i, fileBody);
        }

        builder.addPart("desc", descBody);
        httpPost.setEntity(builder.build());
        HttpResponse response = httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            resultStr = streamToStr(entity.getContent());
        }
        return resultStr;
    }

    public static String streamToStr(InputStream in) throws IOException {
        StringBuilder sbu = new StringBuilder();
        int len;
        byte[] buffer = new byte[1024];
        while ((len = in.read(buffer)) != -1) {
            sbu.append(new String(buffer, 0, len));
        }
        return sbu.toString();
    }
}
