package com.boranget.filesys.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boranget.filesys.entity.po.File;
import com.boranget.filesys.mapper.FileMapper;
import com.boranget.filesys.service.prototype.FileService;

public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {
}
