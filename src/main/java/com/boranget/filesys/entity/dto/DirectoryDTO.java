package com.boranget.filesys.entity.dto;

import com.boranget.filesys.entity.po.Directory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class DirectoryDTO extends Directory {
    private String parentDirId;
}
