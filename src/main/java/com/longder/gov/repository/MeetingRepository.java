package com.longder.gov.repository;

import com.longder.gov.entity.po.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Longder
 */
public interface MeetingRepository extends JpaRepository<Meeting,Long> {

    /**
     * 根据部门id查询会议列表
     * @param deptId
     * @return
     */
    @Query("SELECT M FROM  Meeting M WHERE M.department.id = :deptId")
    List<Meeting> listByDeptId(@Param("deptId") Long deptId);
}
