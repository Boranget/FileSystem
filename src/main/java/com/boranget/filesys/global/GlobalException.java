package com.boranget.filesys.global;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalException extends RuntimeException{
    int code;
    String msg;

    public static GlobalException fail(GlobalCode globalCode) {
        return new GlobalException(globalCode.getCode(), globalCode.getMessage());
    }
    public static GlobalException fail(int code,String msg) {
        return new GlobalException(code, msg);
    }
    public static GlobalException fail(String msg) {
        return fail(500, msg);
    }

    /**
     * 将全局异常包装为全局响应
     * @return
     */
    public GlobalResponse toGlobalResponse(){
        return new GlobalResponse(this.getCode(),this.getMsg(),null);
    }
}
