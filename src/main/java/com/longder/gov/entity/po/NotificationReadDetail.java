package com.longder.gov.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Proxy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 通知阅读详情
 * Created by Longder
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "NOTIFICATION_READ_DETAIL")
@Proxy(lazy = false)
public class NotificationReadDetail extends BaseIdEntity{

    /**
     * 关联通知
     */
    @ManyToOne
    @JoinColumn(name = "notification_id_")
    private Notification notification;

    /**
     * 阅读人
     */
    @ManyToOne
    @JoinColumn(name = "read_user_id_")
    private SysUser readUser;

    /**
     * 阅读时间
     */
    @Column(name = "read_time_")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime readTime;
}
