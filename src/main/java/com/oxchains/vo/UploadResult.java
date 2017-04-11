package com.oxchains.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Result
 * 
 * @author liuruichao
 * Created on 2016-01-15 16:27
 */
public class UploadResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private int status;
    private List<FileUploadResult> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<FileUploadResult> getData() {
        return data;
    }

    public void setData(List<FileUploadResult> data) {
        this.data = data;
    }
}
