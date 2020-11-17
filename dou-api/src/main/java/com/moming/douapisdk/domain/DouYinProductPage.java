package com.moming.douapisdk.domain;

import lombok.Data;

import java.util.List;

@Data
public class DouYinProductPage {

    private int all;
    private int allPages;
    private int count;
    private int currentPage;
    private List<DouYinProduct> data;
    private int pageSize;

    /**
     * 是否有下一页
     * @return 是否
     */
    public boolean hasNext() {
        return (this.allPages - this.currentPage) > 0;
    }

}
