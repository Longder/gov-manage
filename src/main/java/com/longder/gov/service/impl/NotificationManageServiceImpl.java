package com.longder.gov.service.impl;

import com.longder.gov.entity.po.Notification;
import com.longder.gov.entity.po.NotificationReadDetail;
import com.longder.gov.entity.po.SysUser;
import com.longder.gov.repository.NotificationReadDetailRepository;
import com.longder.gov.repository.NotificationRepository;
import com.longder.gov.security.SecurityUtil;
import com.longder.gov.service.NotificationManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 通知管理的相关业务
 * Created by Longder
 */
@Service
public class NotificationManageServiceImpl implements NotificationManageService {

    @Resource
    private NotificationRepository notificationRepository;
    @Resource
    private NotificationReadDetailRepository notificationReadDetailRepository;

    /**
     * 查询某部门下的所有通知
     *
     * @param deptId
     * @return
     */
    @Override
    public List<Notification> listForDept(Long deptId) {
        return notificationRepository.listByDeptId(deptId);
    }

    /**
     * 查询某部门下的所有通知（员工用，包括阅读情况）
     *
     * @param deptId
     * @return
     */
    @Override
    public List<Notification> listForDeptWithEmp() {
        SysUser currentUser = SecurityUtil.getCurrentUser();
        assert currentUser != null;
        List<Notification> notificationList = notificationRepository.listByDeptId(currentUser.getDepartment().getId());
        notificationList.forEach(notification -> {
            int count = notificationReadDetailRepository.countByReadUserIdAndNotificationId(currentUser.getId(), notification.getId());
            if (count > 0) {
                notification.setHasRead(true);
            }else{
                notification.setHasRead(false);
            }
        });
        return notificationList;
    }

    /**
     * 添加一个通知
     *
     * @param notification
     */
    @Override
    @Transactional
    public void addOneNotification(Notification notification) {
        //处理通知的部门
        SysUser currentUser = SecurityUtil.getCurrentUser();
        assert currentUser != null;
        notification.setDepartment(currentUser.getDepartment());
        //时间
        notification.setNotifyTime(LocalDateTime.now());
        notificationRepository.save(notification);
    }

    /**
     * 阅读一个通知
     *
     * @param notificationId
     */
    @Override
    @Transactional
    public void readOneNotification(Long notificationId) {
        Notification notification = notificationRepository.getOne(notificationId);

        NotificationReadDetail readDetail = new NotificationReadDetail();
        readDetail.setNotification(notification);
        readDetail.setReadTime(LocalDateTime.now());
        SysUser currentUser = SecurityUtil.getCurrentUser();
        readDetail.setReadUser(currentUser);
        //记录阅读记录
        notificationReadDetailRepository.save(readDetail);
    }

    /**
     * 查询某个通知的阅读情况
     *
     * @param notificationId
     * @return
     */
    @Override
    public List<NotificationReadDetail> listReadDetailForNotification(Long notificationId) {
        return notificationReadDetailRepository.listByNotificationId(notificationId);
    }

    /**
     * 查询获取一个通知
     *
     * @param notificationId
     * @return
     */
    @Override
    public Notification getOneNotification(Long notificationId) {
        return notificationRepository.getOne(notificationId);
    }
}
