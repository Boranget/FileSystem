package com.boranget.filesys.entity.dto;

import com.boranget.filesys.entity.po.File;
import lombok.Data;

@Data
public class FileDTO extends File {
    private String parentDirId;
}
