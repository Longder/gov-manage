package com.longder.gov.controller;

import com.longder.gov.entity.po.Department;
import com.longder.gov.service.DepartmentManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 部门管理用的Controller
 */
@Controller
@RequestMapping("/admin/department")
public class DepartmentManageController {

    @Resource
    private DepartmentManageService departmentManageService;

    /**
     * 部门列表
     * @return
     */
    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("list",departmentManageService.listAll());
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
        departmentManageService.addOneDepartment(department);
        return "redirect:list";
    }

}
