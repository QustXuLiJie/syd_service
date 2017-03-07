package com.mobanker.shanyidai.esb.common.utils;

import com.mobanker.framework.pojo.Page;
import com.mobanker.framework.pojo.Pageable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
public class Entity implements Pageable, Serializable {
    private static final long serialVersionUID = -7208074780550828689L;

    private Page page = null; //分页工具

    private String product;//产品
    private String channel;//渠道
    private String version;//版本
    private String ip;//ip

    @Override
    public Page setPage(Page page) {
        this.page = page;
        return this.getPage();
    }

    @Override
    public Page getPage() {
        return this.page;
    }

    /**
     * @return {@link #getPage()}
     */
    public Page ensurePage() {
        if (null == this.getPage()) {
            this.setPage(new Page());
        }

        return this.getPage();
    }

}
