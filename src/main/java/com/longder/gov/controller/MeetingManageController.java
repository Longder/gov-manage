package com.longder.gov.controller;

import com.longder.gov.entity.po.Meeting;
import com.longder.gov.entity.po.SysUser;
import com.longder.gov.security.SecurityUtil;
import com.longder.gov.service.MeetingManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 会议管理的控制器
 * Created by Longder
 */
@Controller
@RequestMapping("/admin/meeting")
public class MeetingManageController {

    @Resource
    private MeetingManageService meetingManageService;
    /**
     * 会议列表，领导用
     * @return
     */
    @GetMapping("/listForDept")
    public String listForDept(Model model){
        SysUser currentUser = SecurityUtil.getCurrentUser();
        assert currentUser != null;
        List<Meeting> meetingList = meetingManageService.listForDept(currentUser.getDepartment().getId());
        model.addAttribute("list",meetingList);
        return "meeting/listForDept";
    }

    /**
     * 去发布会议页
     * @return
     */
    @GetMapping("/toAdd")
    public String toAdd(){
        return "meeting/add-meeting-modal";
    }

    /**
     * 添加一个会议
     * @return
     */
    @PostMapping("/add")
    public String add(Meeting meeting){
        meetingManageService.addOneMeeting(meeting);
        return "redirect:listForDept";
    }

    /**
     * 会议详情
     * @param meetingId
     * @return
     */
    @GetMapping("/detail")
    public String detail(Long meetingId,Model model){
        model.addAttribute("meeting",meetingManageService.getOneMeeting(meetingId));
        return "meeting/detail";
    }

    /**
     * 会议列表，员工用
     * @return
     */
    @GetMapping("/listForEmp")
    public String listForEmp(Model model){
        SysUser currentUser = SecurityUtil.getCurrentUser();
        assert currentUser != null;
        List<Meeting> meetingList = meetingManageService.listForDept(currentUser.getDepartment().getId());
        model.addAttribute("list",meetingList);
        return "meeting/listForEmp";
    }

    /**
     * 去填写会议总结
     * @param meetingId
     * @param model
     * @return
     */
    @GetMapping("/toSummary")
    public String toSummary(Long meetingId,Model model){
        model.addAttribute("meeting",meetingManageService.getOneMeeting(meetingId));
        return "meeting/summary";
    }

    @PostMapping("/addSummary")
    public String addSummary(String summaryContent,Long meetingId){
        meetingManageService.summaryMeeting(summaryContent,meetingId);
        return "redirect:listForEmp";
    }
}
