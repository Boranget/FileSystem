package com.boranget.filesys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boranget.filesys.entity.dto.DirectoryDTO;
import com.boranget.filesys.entity.dto.FileDTO;
import com.boranget.filesys.entity.po.File;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileMapper extends BaseMapper<File> {
    List<FileDTO> listAllFileWithParent();
    /**
     * 移动某个文件夹到另一个文件夹下，底层修改或新增dir对应关系
     * @param dirId
     * @param fileId
     */
    void move(@Param("dirId") String dirId, @Param("fileId") String fileId);
}
