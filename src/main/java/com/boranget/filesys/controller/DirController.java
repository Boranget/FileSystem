package com.boranget.filesys.controller;

import com.boranget.filesys.entity.dto.DirectoryDTO;
import com.boranget.filesys.entity.po.Directory;
import com.boranget.filesys.service.TreeService;
import com.boranget.filesys.service.prototype.DirService;
import com.boranget.filesys.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DirController {
    @Autowired
    TreeService treeService;
    @RequestMapping("/dir/add")
    public Result<String> addDir(@RequestBody Map<String,String> param){
        String path = param.get("path");
        String dirName = param.get("dirName");
        DirectoryDTO directoryDTO = new DirectoryDTO();
        directoryDTO.setDirName(dirName);
        treeService.addDir(path,directoryDTO);
        return Result.success("");
    }
}
