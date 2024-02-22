package com.boranget.filesys;

import com.boranget.filesys.entity.dto.DirectoryDTO;
import com.boranget.filesys.entity.dto.FileDTO;
import com.boranget.filesys.entity.po.File;
import com.boranget.filesys.mapper.DirMapper;
import com.boranget.filesys.mapper.FileMapper;
import com.boranget.filesys.service.TreeService;
import com.boranget.filesys.utils.OtherUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

@SpringBootTest
public class ApplicationTest {
    @Autowired
    DirMapper dirMapper;
    @Autowired
    FileMapper fileMapper;
    @Test
    void a() throws IOException {
        fileMapper.insert(File.builder().fileName("a.txt").fileContent("123456".getBytes(StandardCharsets.UTF_8)).fileSize(2).modifyTime(new Date()).build());
        List<FileDTO> fileDTOS = fileMapper.listAllFileWithParent();
        byte[] fileContent = fileDTOS.get(0).getFileContent();
        System.out.println(new String(fileContent));
        System.out.println(new ObjectMapper().writeValueAsString(fileDTOS));

    }
    @Autowired
    TreeService treeService;
    @Test
    void b(){
        dirMapper.move("5","6");
    }
    @Test
    void e(){
        System.out.println(OtherUtils.getRandomId());
    }

}
