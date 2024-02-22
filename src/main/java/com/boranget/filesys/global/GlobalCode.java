package com.boranget.filesys.global;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum GlobalCode {
    SUCCESS(200, "请求成功 ok"),
    PATH_NOT_EXIST(404, "路径不存在"),
    FILE_ALLREADY_EXIST(500, "文件已存在");
    int code;
    String message;


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


}
