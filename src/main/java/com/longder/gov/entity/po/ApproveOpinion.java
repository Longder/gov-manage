package com.longder.gov.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Proxy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 审批意见实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "APPROVE_OPINION")
@Proxy(lazy = false)
public class ApproveOpinion extends BaseIdEntity {

    /**
     * 审批申请的id
     */
    @Column(name = "approve_apply_id_")
    private Long approveApplyId;

    /**
     * 审批意见内容
     */
    @Column(name = "opinion_")
    private String opinion;

    /**
     * 审批人
     */
    @ManyToOne
    @JoinColumn(name = "approve_user_id_")
    private SysUser approveUser;

    /**
     * 填写时间
     */
    @Column(name = "fill_time_")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime fillTime;

}
