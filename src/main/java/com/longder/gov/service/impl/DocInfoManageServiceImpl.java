package com.longder.gov.service.impl;

import com.longder.gov.entity.po.DocInfo;
import com.longder.gov.entity.po.SysUser;
import com.longder.gov.repository.DocInfoRepository;
import com.longder.gov.security.SecurityUtil;
import com.longder.gov.service.DocInfoMangeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DocInfoManageServiceImpl implements DocInfoMangeService {
    @Resource
    private DocInfoRepository docInfoRepository;
    /**
     * 默认上传路径
     */
    @Value("${system.upload-path}")
    private String defaultPath;

    /**
     * 查询部门下的所有文档
     *
     * @param deptId
     * @return
     */
    @Override
    public List<DocInfo> listForDept(Long deptId) {
        return docInfoRepository.listByDeptId(deptId);
    }

    /**
     * 添加一个文档
     *
     * @param docInfo
     */
    @Override
    @Transactional
    public void addOneDocInfo(DocInfo docInfo) {
        //处理部门和上传用户
        SysUser currentUser = SecurityUtil.getCurrentUser();
        assert currentUser != null;
        docInfo.setUploadUser(currentUser);
        docInfo.setDepartment(currentUser.getDepartment());
        //上传时间
        docInfo.setUploadTime(LocalDateTime.now());
        docInfo.setFileName(docInfo.getFile().getOriginalFilename());
        //先存
        docInfoRepository.save(docInfo);
        //存储文件
        String path = storeFile(docInfo.getFile(),docInfo.getId());
        docInfo.setPath(path);
        docInfoRepository.save(docInfo);
    }

    @Override
    public DocInfo getOneDoc(Long docInfoId) {
        return docInfoRepository.getOne(docInfoId);
    }


    /**
     * 存储文件，返回文件路径
     * @param multipartFile
     * @return
     */
    private String storeFile(MultipartFile multipartFile, Long id){
        String filePath = defaultPath+ File.separator + id+"__"+multipartFile.getOriginalFilename();
        File desFile = new File(filePath);
        if(!desFile.getParentFile().exists()){
            desFile.mkdirs();
        }
        try {
            multipartFile.transferTo(desFile);
        } catch (IOException e) {
            System.out.println("上传文件出错");
            e.printStackTrace();
        }
        return filePath;
    }
}
