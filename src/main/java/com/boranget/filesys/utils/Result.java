package com.boranget.filesys.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result<T> {
    private int code;
    private String msg;
    private T content;

    public static <E> Result<E> success(E data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <E> Result<E> fail(E data) {
        return new Result<>(ResultCode.FAIL.getCode(), ResultCode.FAIL.getMessage(), data);
    }

    @AllArgsConstructor
    public enum ResultCode {
        SUCCESS(200, "请求成功 ok"),
        FAIL(500, "出现问题 something is wrong");

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        int code;
        String message;


    }

}

