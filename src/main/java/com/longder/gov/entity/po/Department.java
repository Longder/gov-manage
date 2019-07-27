package com.longder.gov.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 部门实体表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "SYS_DEPARTMENT")
@Proxy(lazy = false)
public class Department extends BaseIdEntity {
    /**
     * 名称
     */
    private String name;

    /**
     * 职能描述
     */
    private String description;
}
