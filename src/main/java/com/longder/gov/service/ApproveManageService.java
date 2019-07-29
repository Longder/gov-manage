package com.longder.gov.service;

import com.longder.gov.entity.po.ApproveApply;
import com.longder.gov.entity.po.ApproveOpinion;

import java.util.List;

/**
 * 审批管理的service
 */
public interface ApproveManageService {

    /**
     * 添加一个审批申请
     * @param approveApply
     */
    void addOneApproveApply(ApproveApply approveApply);

    /**
     * 查询当前员工发出的所有审批
     * @return
     */
    List<ApproveApply> listForEmp();

    /**
     * 查询当前领导所在部门下的所有审批
     * @return
     */
    List<ApproveApply> listForDept();

    /**
     * 获取一个审批申请
     * @param approveApplyId
     * @return
     */
    ApproveApply getOneApproveApply(Long approveApplyId);

    /**
     * 添加一个审批意见
     * @param approveOpinion
     */
    void addOneApproveOpinion(ApproveOpinion approveOpinion);

    List<ApproveOpinion> listOpinionsForApply(Long approveApplyId);

    /**
     * 完成审批
     * @param approveApplyId
     */
    void completeApprove(Long approveApplyId);
}
