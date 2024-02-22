package com.boranget.filesys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Data
public class FileTreeNode {
    /**
     * 类型常量
     */
    public static final String TYPE_FILE = "TYPE_FILE";
    public static final String TYPE_DIR = "TYPE_DIR";
    /**
     * 树节点类型
     */
    private String type;
    /**
     * 节点名字，取文件或者文件的名字
     */
    private String name;
    /**
     * content，存储文件信息或者文件夹信息
     */
    private Object content;
    /**
     * 子节点
     */
    private Map<String, FileTreeNode> childs;

}
