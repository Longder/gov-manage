package com.longder.gov.repository;

import com.longder.gov.entity.po.ApproveApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApproveApplyRepository extends JpaRepository<ApproveApply,Long> {

    @Query("SELECT A FROM ApproveApply A WHERE A.applyUser.id = :applyUserId")
    List<ApproveApply> listByApplyUserId(@Param("applyUserId") Long applyUserId);

    @Query("SELECT A FROM ApproveApply A WHERE A.department.id = :deptId")
    List<ApproveApply> listByDeptId(@Param("deptId")Long deptId);
}
