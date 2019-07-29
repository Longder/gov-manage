package com.longder.gov.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Proxy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 通知实体
 * Created by Longder
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "NOTIFICATION")
@Proxy(lazy = false)
public class Notification extends BaseIdEntity {
    /**
     * 标题
     */
    @Column(name = "title_")
    private String title;
    /**
     * 内容
     */
    @Column(name = "content_")
    private String content;
    /**
     * 发布时间
     */
    @Column(name = "notify_time_")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime notifyTime;

    /**
     * 所属部门
     */
    @ManyToOne
    @JoinColumn(name = "dept_id_")
    private Department department;

    /**
     * 是否已读
     * 非持久化，员工查询展示封装
     */
    @Transient
    private Boolean hasRead;
}
