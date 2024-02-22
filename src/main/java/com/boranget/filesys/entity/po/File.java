package com.boranget.filesys.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("file_table")
public class File {
    String id;
    String fileName;
    byte[] fileContent;
    long fileSize;
    Date modifyTime;
}
