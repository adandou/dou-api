package com.moming.douapisdk;

import com.moming.douapisdk.domain.LogisticsCompany;
import com.moming.douapisdk.request.OrderLogisticsCompanyListRequest;
import com.moming.douapisdk.response.OrderLogisticsCompanyListResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;


@SpringBootTest
@ActiveProfiles
@SpringBootApplication
class DefaultDouYinClientTest {


    @Autowired
    private DefaultDouYinClient defaultDouYinClient;

    @Test
    void execute() {
        OrderLogisticsCompanyListRequest companyListRequest = new OrderLogisticsCompanyListRequest();

        String accessToken = "随便一个有效的accessToken";

        OrderLogisticsCompanyListResponse companyListResponse = defaultDouYinClient.execute(companyListRequest, accessToken);
        List<LogisticsCompany> data = companyListResponse.getData();
        System.out.println(data.size());
    }
}