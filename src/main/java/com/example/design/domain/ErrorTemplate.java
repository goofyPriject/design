package com.example.design.domain;

import lombok.Data;

@Data
public class ErrorTemplate<T> {

    private String errorInfo;

    private String code;

    private T data;

    public ErrorTemplate setCode(String code) {
        this.code = code;
        return this;
    }

    public ErrorTemplate setData(T t) {
        this.data = t;
        return this;
    }

    public ErrorTemplate setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
        return this;
    }

    public static <T> ErrorTemplate<T> success(T t) {
        return (new ErrorTemplate()).setCode("200").setData(t).setErrorInfo("");
    }

}
