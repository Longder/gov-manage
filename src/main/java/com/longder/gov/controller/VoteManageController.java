package com.longder.gov.controller;

import com.longder.gov.entity.po.SysUser;
import com.longder.gov.entity.po.Vote;
import com.longder.gov.security.SecurityUtil;
import com.longder.gov.service.VoteManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 投票管理相关控制器
 * Created by Longder
 */
@Controller
@RequestMapping("/admin/vote")
public class VoteManageController {

    @Resource
    private VoteManageService voteManageService;

    /**
     * 投票列表
     * @return
     */
    @GetMapping("/list")
    public String list(Model model){
        SysUser currentUser = SecurityUtil.getCurrentUser();
        assert currentUser != null;
        model.addAttribute("list",voteManageService.listAllVote(currentUser.getDepartment().getId()));
        return "vote/list";
    }

    /**
     * 去发布投票页面
     * @return
     */
    @GetMapping("/toAdd")
    public String toAdd(){
        return "vote/add-vote-modal";
    }

    /**
     * 新增一个投票
     * @param vote
     * @return
     */
    @PostMapping("/add")
    public String add(Vote vote){
        voteManageService.addOneVote(vote);
        return "redirect:list";
    }

    /**
     * 去参加投票页
     * @param voteId
     * @return
     */
    @GetMapping("/detail")
    public String detail(Long voteId,Model model){
        model.addAttribute("vote",voteManageService.getOneVote(voteId));
        return "vote/detail";
    }

    /**
     * 参加投票
     * @return
     */
    @PostMapping("/addVote")
    public String addVote(Long voteDetailId){
        voteManageService.vote(voteDetailId);
        return "redirect:list";
    }
}
