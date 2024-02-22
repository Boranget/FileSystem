package com.boranget.filesys.controller;

import com.boranget.filesys.global.GlobalCode;
import com.boranget.filesys.global.GlobalException;
import com.boranget.filesys.global.GlobalResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(GlobalException.class)
    public GlobalResponse handleGlobalException(GlobalException globalException){
        return globalException.toGlobalResponse();
    }
}
