package com.boranget.filesys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boranget.filesys.entity.dto.DirectoryDTO;
import com.boranget.filesys.entity.po.Directory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DirMapper extends BaseMapper<Directory> {
    /**
     * 检索出所有的文件夹
     * @return
     */
    List<DirectoryDTO> listAllDirWithParent();

    /**
     * 移动某个文件夹到另一个文件夹下，底层修改或新增dir对应关系
     * @param parentId
     * @param dirId
     */
    void move(@Param("parentId") String parentId, @Param("dirId") String dirId);
}
