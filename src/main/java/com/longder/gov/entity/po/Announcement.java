package com.longder.gov.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 公告实体
 * Created by Longder
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "ANNOUNCEMENT")
@Proxy(lazy = false)
public class Announcement extends BaseIdEntity{
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
}
