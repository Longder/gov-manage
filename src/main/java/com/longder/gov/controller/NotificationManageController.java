package com.longder.gov.controller;

import com.longder.gov.entity.po.Notification;
import com.longder.gov.entity.po.SysUser;
import com.longder.gov.security.SecurityUtil;
import com.longder.gov.service.NotificationManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 通知管理控制器
 * Created by Longder
 */
@Controller
@RequestMapping("/admin/notification")
public class NotificationManageController {

    @Resource
    private NotificationManageService notificationManageService;


    /**
     * 领导查看通知列表
     * @return
     */
    @GetMapping("/listForDept")
    public String listForDept(Model model){
        SysUser currentUser = SecurityUtil.getCurrentUser();
        assert currentUser != null;
        List<Notification> notificationList = notificationManageService.listForDept(currentUser.getDepartment().getId());
        model.addAttribute("list",notificationList);
        return "notification/listForDept";
    }

    /**
     * 员工查看通知列表
     * @return
     */
    @GetMapping("/listForEmp")
    public String listForEmp(Model model){
        model.addAttribute("list",notificationManageService.listForDeptWithEmp());
        notificationManageService.listForDeptWithEmp();
        return "notification/listForEmp";
    }

    /**
     * 添加通知的页面
     * @return
     */
    @GetMapping("/toAdd")
    public String toAdd(){
        return "notification/add-notification-modal";
    }

    /**
     * 新增一个通知
     * @return
     */
    @PostMapping("/add")
    public String add(Notification notification){
        notificationManageService.addOneNotification(notification);
        return "redirect:listForDept";
    }

    /**
     * 通知详情（领导用）
     * @param notificationId
     * @return
     */
    @GetMapping("/detail")
    public String notificationDetail(Long notificationId,Model model){
        model.addAttribute("notification",notificationManageService.getOneNotification(notificationId));
        model.addAttribute("readDetailList",notificationManageService.listReadDetailForNotification(notificationId));
        return "notification/detail";
    }

    /**
     * 通知详情（员工用）
     * @param notificationId
     * @param model
     * @return
     */
    @GetMapping("/detailForEmp")
    public String notificationDetailForEmp(Long notificationId,Model model){
        model.addAttribute("notification",notificationManageService.getOneNotification(notificationId));
        return "notification/detailForEmp";
    }

    /**
     * 阅读通知
     * @return
     */
    @PostMapping("/read")
    public String readNotification(Long notificationId){
        notificationManageService.readOneNotification(notificationId);
        return "redirect:listForEmp";
    }


}
