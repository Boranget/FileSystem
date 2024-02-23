package com.boranget.filesys.controller;

import com.boranget.filesys.entity.global.GlobalException;
import com.boranget.filesys.entity.global.GlobalResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 主动抛出的全局异常的处理
     * @param globalException
     * @return
     */
    @ResponseBody
    @ExceptionHandler(GlobalException.class)
    public GlobalResponse handleGlobalException(GlobalException globalException){
        return globalException.toGlobalResponse();
    }

    /**
     * 非主动抛出的全局异常处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public GlobalResponse handleGlobalException(Exception e){
        return GlobalException.fail(e.getMessage()).toGlobalResponse();
    }
}
