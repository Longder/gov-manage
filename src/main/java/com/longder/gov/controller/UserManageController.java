package com.longder.gov.controller;

import com.longder.gov.entity.po.SysUser;
import com.longder.gov.service.DepartmentManageService;
import com.longder.gov.service.UserManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 用户管理用的Controller
 */
@Controller
@RequestMapping("/admin/user")
public class UserManageController {

    @Resource
    private DepartmentManageService departmentManageService;
    @Resource
    private UserManageService userManageService;

    /**
     * 用户列表
     */
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", userManageService.listAll());
        return "user/list";
    }

    /**
     * 去添加用户的modal
     */
    @GetMapping("/toAdd")
    public String toAdd(Model model) {
        //所有部门
        model.addAttribute("deptList", departmentManageService.listAll());
        return "user/add-user-modal";
    }

    /**
     * 添加用户
     *
     * @param sysUser
     * @return
     */
    @PostMapping("/add")
    public String add(SysUser sysUser) {
        userManageService.addOneUser(sysUser);
        return "redirect:list";
    }

//    /**
//     * 处理角色枚举的传递封装问题
//     * @param webDataBinder
//     */
//    @InitBinder
//    public void initBinder(final WebDataBinder webDataBinder) {
//        webDataBinder.registerCustomEditor(SysRole.class, new SysRoleConverter());
//    }
}
