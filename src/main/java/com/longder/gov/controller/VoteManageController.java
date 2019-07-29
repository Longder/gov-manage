package com.longder.gov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 投票管理相关控制器
 * Created by Longder
 */
@Controller
@RequestMapping("/admin/vote")
public class VoteManageController {

    /**
     * 投票列表
     * @return
     */
    @GetMapping("/list")
    public String list(){
        return "vote/list";
    }
}
