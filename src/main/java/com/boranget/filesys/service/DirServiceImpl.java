package com.boranget.filesys.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boranget.filesys.mapper.DirMapper;
import com.boranget.filesys.entity.po.Directory;
import com.boranget.filesys.service.prototype.DirService;
import org.springframework.stereotype.Service;

@Service
public class DirServiceImpl extends ServiceImpl<DirMapper,Directory> implements DirService {

}
