package com.longder.gov.service.impl;

import com.longder.gov.entity.po.Department;
import com.longder.gov.repository.DepartmentRepository;
import com.longder.gov.service.DepartmentManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentManageServiceImpl implements DepartmentManageService {

    @Resource
    private DepartmentRepository departmentRepository;

    /**
     * 查询所有部门
     *
     * @return
     */
    @Override
    public List<Department> listAll() {
        return departmentRepository.findAll();
    }

    /**
     * 添加一个部门
     *
     * @param department
     */
    @Override
    @Transactional
    public void addOneDepartment(Department department) {
        departmentRepository.save(department);
    }
}
