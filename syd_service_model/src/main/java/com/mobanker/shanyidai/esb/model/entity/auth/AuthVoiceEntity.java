package com.mobanker.shanyidai.esb.model.entity.auth;

import com.mobanker.framework.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Table;
import java.util.Date;

/**
 * @desc: 语音识别功能记录
 * @author: Richard Core
 * @create time: 2017/2/16 13:37
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Table(name = "syd_auth_voice_record")
public class AuthVoiceEntity extends BaseEntity {
//    private Long id;   //id
    private String status;   //认证记录状态  VoiceOrderStatusEnum
    /* 上传参数*/
    private Long userId;   //用户id
    private String orderNo;   //科大讯飞订单号
    private String fileName;   //文件名称
    private String voiceLast;   //语音时长
    private String fileSize;   //文件大小 单位字节
    private String filePath;   //文件路径

    private Date uploadBeginTime;   //录音开始上传时间
    private Date uploadTime;   //录音上传时间
    private Long uploadDuration;//录音上传消耗时间
    private String uploadSid;   //上传语音流水号

    /*MQ回调参数*/
    private String billNo;   //认证系统流水号(mq返回)
    private String skuNumber;   //唯一标示(认证系统mq返回)
    private String audioId;   //认证系统音频ID
    private Date mqCallTime;   //MQ解析回调时间
    private String voiceText;   //语音解析结果文本
    private String mqResultStatus;   //语音解析状态
    private Long transDuration;   //认证系统解析时长

    /*es比对*/
    private Date esCompareTime;   //ES处理时间
    private Double textPassRate;   //预设文本通过率
    private Double keyWordPassRate;   //关键字通过率
    private String esResult;   //ES处理结果

    private String askUserName;   //申请人姓名
    private String askUserPhone;   //申请人手机号
    private String auditRemark;   //审核备注
    private String supervisorRemark;   //主管备注
    private String auditUserName;   //审核人
    private Date auditTime;   //审核时间
    private String beforeAuditStatus;   //审核前状态
    private Long auditUserId;   //审核人ID
    private String detailReason;   //详细原因
    private Long defaultTextId;   //预设文字ID
}
