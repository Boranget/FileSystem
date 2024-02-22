package com.boranget.filesys.global;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalResponse<T> {
    private int code;
    private String msg;
    private T content;

    public static <E> GlobalResponse<E> success(E data) {
        return new GlobalResponse<>(GlobalCode.SUCCESS.getCode(), GlobalCode.SUCCESS.getMessage(), data);
    }

}

