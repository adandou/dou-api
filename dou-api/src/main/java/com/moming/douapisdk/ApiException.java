package com.moming.douapisdk;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DouYin客户端异常
 *
 * @author tianzong
 * @date 2020/7/23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ApiException  extends RuntimeException {

    private static final long serialVersionUID = 8585811867368040814L;


    private String errNo;
    private String message;

    public ApiException(Throwable cause) {
        super(cause);
    }
}
