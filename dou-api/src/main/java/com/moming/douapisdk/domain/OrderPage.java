package com.moming.douapisdk.domain;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author tianzong
 * @date 2020/7/23
 */
@Data
public class OrderPage implements Serializable {

    private static final long serialVersionUID = 4785714500975146363L;

    private Long total;

    private Long count;

    private List<Order> list;

}
