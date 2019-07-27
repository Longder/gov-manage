package com.longder.gov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户管理用的Controller
 */
@Controller
@RequestMapping("/admin/user")
public class UserManageController {

    /**
     * 用户列表
     */
    @GetMapping("/list")
    public String list(){
        return "user/list";
    }

    /**
     * 去添加用户的modal
     */
    @GetMapping("/toAdd")
    public String toAdd(){
        return "user/add-user-modal";
    }
}
