package com.longder.gov.repository;

import com.longder.gov.entity.po.ApproveOpinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApproveOpinionRepository extends JpaRepository<ApproveOpinion,Long> {

    /**
     * 根据申请id查询审批意见
     * @param approveApplyId
     * @return
     */
    @Query("SELECT O FROM ApproveOpinion O WHERE O.approveApplyId = :approveApplyId")
    List<ApproveOpinion> listByApplyId(@Param("approveApplyId") Long approveApplyId);
}
