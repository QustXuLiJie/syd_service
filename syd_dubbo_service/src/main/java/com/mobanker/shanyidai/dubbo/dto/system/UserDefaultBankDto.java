package com.mobanker.shanyidai.dubbo.dto.system;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * @author hantongyang
 * @description
 * @time 2017/2/14 10:19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class UserDefaultBankDto extends Entity{

    private Long id;   //id
    private Date createTime;   //创建时间
    private String createUser;   //创建人
    private Date updateTime;   //修改时间
    private String updateUser;   //修改人
    private String status;   //状态，1有效，0无效
    private Long userId;   //用户ID
    private String creditBankCard;   //默认入账银行卡
}
