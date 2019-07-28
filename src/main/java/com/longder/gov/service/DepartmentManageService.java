package com.longder.gov.service;

import com.longder.gov.entity.po.Department;

import java.util.List;

/**
 * 部门管理的service
 */
public interface DepartmentManageService {

    /**
     * 查询所有部门
     * @return
     */
    List<Department> listAll();

    /**
     * 添加一个部门
     * @param department
     */
    void addOneDepartment(Department department);
}
