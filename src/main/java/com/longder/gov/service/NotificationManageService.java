package com.longder.gov.service;

import com.longder.gov.entity.po.Notification;
import com.longder.gov.entity.po.NotificationReadDetail;

import java.util.List;

/**
 * 通知管理的相关业务
 * Created by Longder
 */
public interface NotificationManageService {

    /**
     * 查询某部门下的所有通知(领导用)
     *
     * @param deptId
     * @return
     */
    List<Notification> listForDept(Long deptId);

    /**
     * 查询某部门下的所有通知（员工用，包括阅读情况）
     *
     * @param deptId
     * @return
     */
    List<Notification> listForDeptWithEmp();

    /**
     * 添加一个通知
     *
     * @param notification
     */
    void addOneNotification(Notification notification);

    /**
     * 阅读一个通知
     *
     * @param notificationId
     */
    void readOneNotification(Long notificationId);

    /**
     * 查询某个通知的阅读情况
     *
     * @param notificationId
     * @return
     */
    List<NotificationReadDetail> listReadDetailForNotification(Long notificationId);

    /**
     * 查询获取一个通知
     *
     * @param notificationId
     * @return
     */
    Notification getOneNotification(Long notificationId);
}