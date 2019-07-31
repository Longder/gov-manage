package com.longder.gov.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Proxy;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 文档信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "DOC_INFO")
@Proxy(lazy = false)
public class DocInfo extends BaseIdEntity {

    /**
     * 文档名称
     */
    @Column(name = "name_")
    private String name;

    /**
     * 原文件名
     */
    @Column(name = "file_name_")
    private String fileName;

    /**
     * 文档路径
     */
    @Column(name = "path_")
    private String path;

    /**
     * 上传用户
     */
    @ManyToOne
    @JoinColumn(name = "upload_user_id_")
    private SysUser uploadUser;

    /**
     * 上传时间
     */
    @Column(name = "upload_time_")
    private LocalDateTime uploadTime;

    /**
     * 所属部门
     */
    @ManyToOne
    @JoinColumn(name = "dept_id_")
    private Department department;

    /**
     * 上传过来的文件
     */
    @Transient
    private MultipartFile file;
}
