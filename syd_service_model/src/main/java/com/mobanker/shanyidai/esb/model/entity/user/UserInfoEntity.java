/**
 * 
 */
package com.mobanker.shanyidai.esb.model.entity.user;

import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.mobanker.framework.entity.BaseEntity;

/**
 * 用户信息实体对象
 * @author chenjianping
 * @data 2016年12月16日
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Table(name = "yyd_users")
public class UserInfoEntity extends BaseEntity {
	private static final long serialVersionUID = 4396512186212445192L;

}
