package com.mobanker.shanyidai.esb.call.util;

import lombok.Data;

import java.io.InputStream;

/**
 * @author 管黎明
 *         <p>
 *         创建时间:2015年10月14日
 */
@Data
public class MultipartFormDataFile {
    /**
     * 字段名
     **/
    private String fieldName;
    /**
     * 文件名
     **/
    private String fileName;
    /**
     * 文件类型
     **/
    private String contentType;
    /**
     * 文件流
     **/
    private InputStream fileStream;

    public MultipartFormDataFile() {

    }

    public MultipartFormDataFile(String fieldName, String fileName, String contentType, InputStream fileStream) {
        this.fieldName = fieldName;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileStream = fileStream;
    }


}
