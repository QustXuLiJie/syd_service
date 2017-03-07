package com.mobanker.shanyidai.dubbo.dto.product;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author hantongyang
 * @description
 * @time 2016/12/30 17:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ProductDto extends Entity{

    private String period;
    private String productType;
    private String borrowType;
    private String merchant;
    private String appVersion;

}
