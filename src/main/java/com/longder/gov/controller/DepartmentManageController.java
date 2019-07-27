package com.longder.gov.controller;

import com.longder.gov.entity.po.Department;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 部门管理用的Controller
 */
@Controller
@RequestMapping("/admin/department")
public class DepartmentManageController {

    /**
     * 部门列表
     * @return
     */
    @GetMapping("/list")
    public String list(){
        return "department/list";
    }

    /**
     * 去添加部门页
     * @return
     */
    @GetMapping("/toAdd")
    public String toAdd(){
        return "department/add-department-modal";
    }

    /**
     * 添加一个部门
     * @return
     */
    @PostMapping("/add")
    public String add(Department department){
        return "redirect:list";
    }

}
