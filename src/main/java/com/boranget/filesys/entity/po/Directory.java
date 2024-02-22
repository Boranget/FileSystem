package com.boranget.filesys.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("dir_table")
public class Directory {
    private String id;
    private String dirName;
    private String dirType;
    private String montId;
}
