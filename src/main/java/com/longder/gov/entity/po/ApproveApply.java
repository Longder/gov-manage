package com.longder.gov.entity.po;

import com.longder.gov.entity.enumeration.ApproveState;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Proxy;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

/**
 * 审批申请
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "APPROVE_APPLY")
@Proxy(lazy = false)
public class ApproveApply extends BaseIdEntity {
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
     * 文档路径
     */
    @Column(name = "file_path_")
    private String filePath;
    /**
     * 文档名称
     */
    @Column(name = "file_name_")
    private String fileName;
    /**
     * 审批状态：
     *
     */
    @Column(name = "approve_state_")
    @Enumerated(value = EnumType.STRING)
    private ApproveState approveState;

    /**
     * 所属部门
     */
    @ManyToOne
    @JoinColumn(name = "dept_id_")
    private Department department;

    /**
     * 申请人
     */
    @ManyToOne
    @JoinColumn(name = "apply_user_id_")
    private SysUser applyUser;

    /**
     * 上传过来的文件
     */
    @Transient
    private MultipartFile file;
}
