# dou-api

#### 介绍
dou API简单封装

#### 使用说明

#####  使用SpringBoot框架，在yml中配置dou API密钥

```java
  # dou api配置
  dou-yin:
    app-key: 123456
    app-secret: abcdefg
    version: 2
    app-host: xxx.com
```



#### 调用接口

 1. 请求接口封装请继承 BaseDouYinRequest类，请求响应继承 BaseDouYinResponse

 2. 例如 调用dou订单列表接口 OrderListRequest

    ```java
    @EqualsAndHashCode(callSuper = true)
    @Data
    public class OrderListRequest extends BaseDouYinRequest<OrderListResponse> {
    
        /**
         * 按创建时间排序
         */
        public final static String ORDER_BY_FIELD_CREATE = "create_time";
    
        /**
         * 按更新时间排序
         */
        public final static String ORDER_BY_FIELD_UPDATE = "update_time";
    
        /**
         * 子订单状态
         */
        private String orderStatus;
    
        /**
         * 开始时间
         */
        private LocalDateTime startTime;
    
        /**
         * 开始时间
         */
        private LocalDateTime endTime;
    
        /**
         * 搜索时间条件：按订单创建时间create_time；按订单更新时间进行搜索update_time
         * 默认创建时间
         */
        private String orderBy;
        /**
         * 订单排序方式：设置了此字段即为desc(最近的在前)
         * 默认为asc（最近的在后）
         */
        private String isDesc;
    
        /**
         * 页数（默认为0，第一页从0开始）
         */
        private String page;
    
        /**
         * 每页订单数（默认为10，最大100）
         */
        private String size;
    
        /**
         * 获取api的url地址
         *
         * @return url地址
         */
        @Override
        public String getApiUrl() {
            return "/order/list";
        }
    
        @Override
        public String getApiMethodName() {
            return "order.list";
        }
    
        /**
         * 获取所有的key-value形式的文本请求参数集合，其中：
         * <ul>
         *     <li>Key: 请求参数名</li>
         *     <li>Value: 请求参数值</li>
         * </ul>
         *
         * @return 文本请求参数集合
         */
        @Override
        public Map<String, String> getTextParams() {
            DouYinHashMap textParams = new DouYinHashMap();
            textParams.put("order_status", this.orderStatus);
            textParams.put("start_time", this.startTime);
            textParams.put("end_time", this.endTime);
            textParams.put("order_by", this.orderBy);
            textParams.put("is_desc", this.isDesc);
            textParams.put("page", this.page);
            textParams.put("size", this.size);
    
            return textParams;
        }
    
        /**
         * 获取具体响应实现类的定义。
         */
        @Override
        public Class<OrderListResponse> getResponseClass() {
            return OrderListResponse.class;
        }
    }
    ```

    ​	

    OrderListResponse

    ```
    @EqualsAndHashCode(callSuper = true)
    @Data
    public class OrderListResponse extends BaseDouYinResponse {
    
        private static final long serialVersionUID = -7698845462133858817L;
    
        private OrderPage data;
    
    }
    ```

    ​	

    测试调用接口

    ```
    @Autowired
    private DefaultDouYinClient defaultDouYinClient;
    
    @Test
    void getApi() throws ApiException {
        OrderListRequest request = new OrderListRequest();
        request.setStartTime(LocalDateTime.now().plusDays(-10));
        request.setEndTime(LocalDateTime.now());
        request.setOrderBy(OrderListRequest.ORDER_BY_FIELD_CREATE);
        request.setPage("1");
        request.setSize("10");
    
        OrderListResponse execute = defaultDouYinClient.execute(request, "xxxxxxxx");
        assertEquals(execute.getErrNo(), 0);
        System.out.println(execute.getData().getTotal());
    }
    ```



