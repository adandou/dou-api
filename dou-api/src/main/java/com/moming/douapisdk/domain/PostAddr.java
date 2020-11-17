package com.moming.douapisdk.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author tianzong
 * @date 2020/7/23
 */
@Data
public class PostAddr {

    private City city;
    private String detail;
    private Province province;
    private Town town;

    @Data
    private static class City {
        /**
         * id : 150100
         * name : 呼和浩特市
         */

        private String id;
        private String name;


    }

    @Setter
    @Getter
    @ToString
    private static class Province {
        /**
         * id : 150000
         * name : 内蒙古自治区
         */

        private String id;
        private String name;


    }

    @Setter
    @Getter
    @ToString
    private static class Town {
        /**
         * id : 150101
         * name : 市辖区
         */

        private String id;
        private String name;


    }

}
