package com.moming.douapisdk;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * exception process
 *
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
