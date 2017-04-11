package com.oxchains.vo;

import java.io.Serializable;

/**
 * 上传文件结果
 * 
 * @author liuruichao
 * Created on 2016-01-15 14:18
 */
public class FileUploadResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private int status;
    private String fileName;
    private String url;

    public FileUploadResult() {
    }

    public FileUploadResult(int status, String fileName, String url) {
        this.status = status;
        this.fileName = fileName;
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
