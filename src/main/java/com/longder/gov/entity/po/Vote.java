package com.longder.gov.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 * 投票实体
 * Created by Longder
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "VOTE")
@Proxy(lazy = false)
public class Vote extends BaseIdEntity {

    /**
     * 标题
     */
    @Column(name = "title_")
    private String title;

    /**
     * 描述
     */
    @Column(name = "description_")
    private String description;

    /**
     * 所属部门
     */
    @ManyToOne
    @JoinColumn(name = "dept_id_")
    private Department department;
}
