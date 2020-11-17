package com.moming.douapisdk.response;


import com.moming.douapisdk.BaseDouYinResponse;
import com.moming.douapisdk.domain.LogisticsCompany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author tianzong
 * @date 2020/7/23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderLogisticsCompanyListResponse extends BaseDouYinResponse {
    private static final long serialVersionUID = -1749462435116913793L;

    private List<LogisticsCompany> data;
}
