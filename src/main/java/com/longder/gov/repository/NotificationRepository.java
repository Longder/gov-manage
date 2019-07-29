package com.longder.gov.repository;

import com.longder.gov.entity.po.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Longder
 */
public interface NotificationRepository extends JpaRepository<Notification,Long> {

    /**
     * 根据部门id查询
     * @param deptId
     * @return
     */
    @Query("SELECT N FROM Notification N WHERE N.department.id = :deptId")
    List<Notification> listByDeptId(@Param("deptId") Long deptId);
}
