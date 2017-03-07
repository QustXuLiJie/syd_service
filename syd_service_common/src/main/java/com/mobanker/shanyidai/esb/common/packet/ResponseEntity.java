package com.mobanker.shanyidai.esb.common.packet;

import com.alibaba.druid.wall.violation.ErrorCode;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.ToString;

/**
 * 响应报文消息体
 *
 * @author chenjianping
 * @data 2016年12月15日
 */
@Data
@ToString(callSuper = true)
public class ResponseEntity extends Entity {

    private static final long serialVersionUID = 6059996558181243521L;

    private String status;//调用接口是否成功状态 1 成功  0 失败
    private String error;//返回码  成功为00000000  异常返回2位产品代码 + 6位异常码
    private String msg;//异常原因描述
    private Object data;//返回结果
    private String pageCount;
    public ResponseEntity() {

    }

    public ResponseEntity(String status) {
        init(status, null, null, null);
    }

    public ResponseEntity(String status, String error) {
        init(status, error, null, null);
    }

    public ResponseEntity(String status, Object data) {
        init(status, null, null, data);
    }

    public ResponseEntity(String status, String error, String msg) {
        init(status, error, msg, null);
    }

    public ResponseEntity(String status, ErrorConstant error){
        init(status, error.getCode(), error.getDesc(), null);
    }

    public ResponseEntity(String status, String error, String msg, Object data) {
        init(status, error, msg, data);
    }

    private void init(String status, String error, String msg, Object data) {
        this.status = status;
        this.error = error;
        this.msg = msg;
        this.data = data;
    }
}