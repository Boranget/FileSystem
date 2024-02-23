package com.boranget.filesys.controller;

import com.boranget.filesys.entity.dto.FileDTO;
import com.boranget.filesys.service.TreeService;
import com.boranget.filesys.entity.global.GlobalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class FileController {
    @Autowired
    TreeService treeService;

    @RequestMapping("/file/add")
    public GlobalResponse<String> addFile(@RequestParam(value = "file") MultipartFile file, @RequestParam("path") String path) {
        FileDTO fileDTO = new FileDTO();
        fileDTO.setFileName(file.getOriginalFilename());
        fileDTO.setFileSize(file.getSize());
        ByteArrayOutputStream byteArrayOutputStream = null;
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buf)) != -1) {
                byteArrayOutputStream.write(buf, 0, len);
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            fileDTO.setFileContent(byteArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        treeService.addFile(path, fileDTO);
        return GlobalResponse.success("");
    }
}
