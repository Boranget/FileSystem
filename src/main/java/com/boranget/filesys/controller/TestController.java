package com.boranget.filesys.controller;

import jakarta.annotation.Nullable;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TestController {
    @RequestMapping("/hello")
    public String hello(HttpServletRequest httpServletRequest){
        ServletInputStream inputStream = null;
        try {
            inputStream = httpServletRequest.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 使用inputStream.available()并不总是返回整个流的大小。它只返回可以无阻塞地从此输入流读取（或跳过）的字节数的估计值。
        // 在实际应用中，这可能导致只读取了部分数据。
        // 获取不到完整的请求体内容是因为HTTP请求的正文（body）长度是动态的，而inputStream.available()方法返回的是在不阻塞的情况下可以从输入流中读取的字节数。当输入流中的数据量少于或等于输入流内部的缓冲区大小时，available()方法可能返回的是小于实际数据量的值，这就会导致您只读取了部分数据。
        // 为了完整地读取HTTP请求的正文内容，您需要使用一个循环来不断读取输入流直到没有更多数据可读。

        byte[] buffer = new byte[1024];
        StringBuilder resBuilder= new StringBuilder();
        int flag = -1;
        try {
            while ((flag = inputStream.read(buffer))!=-1){
                resBuilder.append(new String(buffer,0,flag));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String a = resBuilder.toString();
        String x = "hello " + a;
        System.out.println(x);
        return x;
    }
}
