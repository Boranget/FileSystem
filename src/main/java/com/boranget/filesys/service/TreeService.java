package com.boranget.filesys.service;

import com.boranget.filesys.entity.dto.DirectoryDTO;
import com.boranget.filesys.entity.dto.FileDTO;
import com.boranget.filesys.entity.FileTreeNode;
import com.boranget.filesys.entity.global.GlobalCode;
import com.boranget.filesys.entity.global.GlobalException;
import com.boranget.filesys.mapper.DirMapper;
import com.boranget.filesys.mapper.FileMapper;
import com.boranget.filesys.utils.OtherUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TreeService {
    @Autowired
    DirMapper dirMapper;
    @Autowired
    FileMapper fileMapper;
    public static FileTreeNode fileTree;

    /**
     * 组合出当前系统中的文件目录树
     */
    @PostConstruct
    private void init() {
        // 装配文件夹
        List<DirectoryDTO> directoryDTOS = dirMapper.listAllDirWithParent();
        // id,树节点
        Map<String, FileTreeNode> fileTreeNodeMap = new HashMap<>();
        directoryDTOS.forEach(d -> {
            FileTreeNode treeDTO = new FileTreeNode(FileTreeNode.TYPE_DIR, d.getDirName(), d, new HashMap<>());
            fileTreeNodeMap.put(d.getId(), treeDTO);
            if ("0".equals(((DirectoryDTO) treeDTO.getContent()).getId())) {
                fileTree = treeDTO;
            }
        });
        fileTreeNodeMap.values().forEach(n -> {
            if (n.getName().equals("/")) {
                return;
            }
            String parentDirId = ((DirectoryDTO) n.getContent()).getParentDirId();
            if (parentDirId != null) {
                fileTreeNodeMap.get(parentDirId).getChilds().put(n.getName(), n);
            }
        });
        // 装配文件
        List<FileDTO> fileDTOS = fileMapper.listAllFileWithParent();
        fileDTOS.stream().forEach(f -> {
            FileTreeNode fileTreeDTO = new FileTreeNode(FileTreeNode.TYPE_FILE, f.getFileName(), f, null);
            String parentDirId = f.getParentDirId();
            fileTreeNodeMap.get(parentDirId).getChilds().put(f.getFileName(), fileTreeDTO);
        });
        showTree();
    }

    private void showTree() {
        try {
            String s = new ObjectMapper().writeValueAsString(fileTree);
            System.out.println(s);
        } catch (JsonProcessingException e) {
            throw GlobalException.fail(e.getMessage());
        }
    }

    public void addFile(String path, FileDTO fileDTO) {
        // 判断插入位置是否存在（获取父文件夹id）
        FileTreeNode location = getLocation(path);
        if (location == null) {
            throw GlobalException.fail(GlobalCode.PATH_NOT_EXIST);
        }
        // 判断是否存在同名文件
        boolean existSameNameFile = ifExistSameNameFile(location, fileDTO.getFileName());
        if(existSameNameFile){
            throw GlobalException.fail(GlobalCode.FILE_ALLREADY_EXIST);
        }
        // 构fileDTO，手动设置id，插入数据库
        String randomId = OtherUtils.getRandomId();
        fileDTO.setId(randomId);
        fileDTO.setParentDirId(((DirectoryDTO) location.getContent()).getId());
        fileMapper.insert(fileDTO);
        // 设置该文件夹的位置
        fileMapper.move(((DirectoryDTO) location.getContent()).getId(), randomId);
        // 清除文件内容后再存入目录树避免内存占用
        fileDTO.setFileContent(null);
        // 添加到树节点
        addNode(location, new FileTreeNode(FileTreeNode.TYPE_FILE, fileDTO.getFileName(), fileDTO, null));
        showTree();
    }

    public void addDir(String path, DirectoryDTO directoryDTO) {
        // 判断插入位置是否存在（获取父文件夹id）
        FileTreeNode location = getLocation(path);
        if (location == null) {
            throw GlobalException.fail(GlobalCode.PATH_NOT_EXIST);
        }
        // 判断是否存在同名文件
        boolean existSameNameFile = ifExistSameNameFile(location, directoryDTO.getDirName());
        if(existSameNameFile){
            throw GlobalException.fail(GlobalCode.FILE_ALLREADY_EXIST);
        }
        // 构造dirDTO，手动设置id，插入数据库
        String randomId = OtherUtils.getRandomId();
        directoryDTO.setId(randomId);
        directoryDTO.setParentDirId(((DirectoryDTO) location.getContent()).getId());
        dirMapper.insert(directoryDTO);
        // 设置该文件夹的位置
        dirMapper.move(((DirectoryDTO) location.getContent()).getId(), randomId);
        // 添加到树节点
        addNode(location, new FileTreeNode(FileTreeNode.TYPE_DIR, directoryDTO.getDirName(), directoryDTO, new HashMap<>()));
        showTree();
    }

    /**
     * 用于进行目录结构抽象管理
     */

    private void addNode(FileTreeNode location, FileTreeNode fileTreeNode) {
        location.getChilds().put(fileTreeNode.getName(), fileTreeNode);
    }

    private FileTreeNode getLocation(String path) {
        if (path == null || path.length() == 0) {
            return null;
        }
        String[] split = path.split("[\\\\/]");
        FileTreeNode currentLocation = fileTree;
        if (split.length == 0 && (path.equals("/") || path.equals("\\"))) {
            // 如果path只有一个分隔符，也就是根路径的意思，切割完后数组为空
            currentLocation = fileTree;
        } else if (split.length == 1) {
            // 这种情况：a/，这是错误的路径形式
            return null;
        } else {
            for (int i = 1; i < split.length; i++) {
                if (currentLocation.getChilds().containsKey(split[i])) {
                    currentLocation = currentLocation.getChilds().get(split[i]);
                } else {
                    return null;
                }
            }
        }
        return currentLocation;
    }

    private boolean ifExistSameNameFile(FileTreeNode location, String name) {
        Map<String, FileTreeNode> childs = location.getChilds();
        return childs.containsKey(name);
    }

}
