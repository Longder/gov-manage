package com.longder.gov.repository;

import com.longder.gov.entity.po.NotificationReadDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Longder
 */
public interface NotificationReadDetailRepository extends JpaRepository<NotificationReadDetail,Long> {

    /**
     * 根据阅读用户、通知统计通知的阅读情况
     * @param readUserId
     * @return
     */
    @Query("SELECT count(D.id) FROM NotificationReadDetail D WHERE D.readUser.id = :readUserId and D.notification.id = :notificationId")
    int countByReadUserIdAndNotificationId(@Param("readUserId") Long readUserId,@Param("notificationId") Long notificationId);

    /**
     * 根据通知查询某通知下的阅读情况
     * @param notificationId
     * @return
     */
    @Query("SELECT D FROM NotificationReadDetail D WHERE D.notification.id = :notificationId")
    List<NotificationReadDetail> listByNotificationId(@Param("notificationId") Long notificationId);
}
