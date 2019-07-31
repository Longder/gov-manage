package com.longder.gov.repository;


import com.longder.gov.entity.po.DocInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocInfoRepository extends JpaRepository<DocInfo,Long> {

    @Query("SELECT D FROM DocInfo D WHERE D.department.id = :deptId")
    List<DocInfo> listByDeptId(Long deptId);
}
