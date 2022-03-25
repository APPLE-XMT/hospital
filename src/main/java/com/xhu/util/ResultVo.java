package com.xhu.util;

import lombok.Data;

@Data
public class ResultVo<T> {
    private String status;

    private String message;

    private T data;
}
