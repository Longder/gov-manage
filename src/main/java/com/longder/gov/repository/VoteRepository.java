package com.longder.gov.repository;

import com.longder.gov.entity.po.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote,Long> {

    /**
     * 根据部门Id查询
     * @param deptId
     * @return
     */
    @Query("SELECT V FROM Vote V WHERE V.department.id = :deptId")
    List<Vote> listByDeptId(@Param("deptId") Long deptId);
}
