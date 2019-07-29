package com.longder.gov.service.impl;

import com.longder.gov.entity.enumeration.ApproveState;
import com.longder.gov.entity.po.ApproveApply;
import com.longder.gov.entity.po.ApproveOpinion;
import com.longder.gov.entity.po.SysUser;
import com.longder.gov.repository.ApproveApplyRepository;
import com.longder.gov.repository.ApproveOpinionRepository;
import com.longder.gov.security.SecurityUtil;
import com.longder.gov.service.ApproveManageService;
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
public class ApproveManageServiceImpl implements ApproveManageService {

    @Resource
    private ApproveApplyRepository approveApplyRepository;
    @Resource
    private ApproveOpinionRepository approveOpinionRepository;

    /**
     * 默认上传路径
     */
    @Value("${system.upload-path}")
    private String defaultPath;

    /**
     * 添加一个审批申请
     *
     * @param approveApply
     */
    @Override
    @Transactional
    public void addOneApproveApply(ApproveApply approveApply) {
        //先存数据库
        SysUser currentUser = SecurityUtil.getCurrentUser();
        approveApply.setApplyUser(currentUser);
        assert currentUser != null;
        approveApply.setDepartment(currentUser.getDepartment());
        approveApply.setFileName(approveApply.getFile().getOriginalFilename());
        approveApply.setApproveState(ApproveState.WAITING);
        approveApplyRepository.save(approveApply);
        //处理文件
        String filePath = storeFile(approveApply.getFile(),approveApply.getId());
        approveApply.setFilePath(filePath);
        approveApplyRepository.save(approveApply);
    }

    /**
     * 查询当前员工发出的所有审批
     *
     * @return
     */
    @Override
    public List<ApproveApply> listForEmp() {
        SysUser currentUser = SecurityUtil.getCurrentUser();
        assert currentUser != null;
        return approveApplyRepository.listByApplyUserId(currentUser.getId());
    }

    /**
     * 查询当前领导所在部门下的所有审批
     *
     * @return
     */
    @Override
    public List<ApproveApply> listForDept() {
        SysUser currentUser = SecurityUtil.getCurrentUser();
        assert currentUser != null;
        return approveApplyRepository.listByDeptId(currentUser.getDepartment().getId());
    }

    /**
     * 获取一个审批申请
     *
     * @param approveApplyId
     * @return
     */
    @Override
    public ApproveApply getOneApproveApply(Long approveApplyId) {
        return approveApplyRepository.getOne(approveApplyId);
    }

    /**
     * 添加一个审批意见
     * @param approveOpinion
     */
    @Override
    @Transactional
    public void addOneApproveOpinion(ApproveOpinion approveOpinion) {
        //当前用户
        SysUser currentUser = SecurityUtil.getCurrentUser();
        approveOpinion.setApproveUser(currentUser);
        //当前时间
        approveOpinion.setFillTime(LocalDateTime.now());

        approveOpinionRepository.save(approveOpinion);

        //修改审批意见的状态
        ApproveApply apply = approveApplyRepository.getOne(approveOpinion.getApproveApplyId());
        apply.setApproveState(ApproveState.APPROVING);
        approveApplyRepository.save(apply);
    }

    @Override
    public List<ApproveOpinion> listOpinionsForApply(Long approveApplyId) {
        return approveOpinionRepository.listByApplyId(approveApplyId);
    }

    /**
     * 完成审批
     *
     * @param approveApplyId
     */
    @Override
    @Transactional
    public void completeApprove(Long approveApplyId) {
        ApproveApply approveApply = approveApplyRepository.getOne(approveApplyId);
        approveApply.setApproveState(ApproveState.FINISHED);
        approveApplyRepository.save(approveApply);
    }

    /**
     * 存储文件，返回文件路径
     * @param multipartFile
     * @return
     */
    private String storeFile(MultipartFile multipartFile,Long id){
        String filePath = defaultPath+File.separator + id+"_"+multipartFile.getOriginalFilename();
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
