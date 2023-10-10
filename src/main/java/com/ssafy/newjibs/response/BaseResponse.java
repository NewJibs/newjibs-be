package com.ssafy.newjibs.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse<T> {
    private Integer status;
    private String message;
    private T data;

    public BaseResponse(ResponseStatus responseStatus, T data) {
        this.status = responseStatus.getStatus();
        this.message = responseStatus.getMessage();
        this.data = data;
    }

    public BaseResponse(ResponseStatus responseStatus) {
        this(responseStatus, null);
    }
}
