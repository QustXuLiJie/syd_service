/**
 * description
 *
 * @author daiyulin
 * @date 创建时间：2016年9月19日 上午11:22:33
 */
package com.mobanker.shanyidai.dubbo.dto.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * package com.mobanker.cust.jobinfo.contract.params;
 *
 * @author daiyulin
 * @date 创建时间：2016年9月19日 上午11:22:33
 * @version 1.0
 * @parameter
 * @return
 */

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class CreditInfoParamsDto implements Serializable {

    private static final long serialVersionUID = 1L;
    // 用户id
    private Long userId;
    //用户姓名
    private String cardId;
    //用户身份证号
    private String realName;
    // 查询字段列表
    private List<String> fields;
    // 用户类型
    private String type;
    //保存字段
    private Map<String, Object> saveFields;
    //协议参数
    private Map<String, Object> commonFields;

}
