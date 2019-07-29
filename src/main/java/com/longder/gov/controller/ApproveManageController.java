package com.longder.gov.controller;

import com.longder.gov.entity.po.ApproveApply;
import com.longder.gov.entity.po.ApproveOpinion;
import com.longder.gov.service.ApproveManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 审批管理的Controller
 */
@Controller
@RequestMapping("/admin/approve")
public class ApproveManageController {

    @Resource
    private ApproveManageService approveManageService;

    /**
     * 员工的审批申请列表
     */
    @GetMapping("/listForEmp")
    public String listForEmployee(Model model) {
        model.addAttribute("list", approveManageService.listForEmp());
        return "approve/listForEmp";
    }

    /**
     * 去添加审批页
     */
    @GetMapping("/toAdd")
    public String toAddApprove() {
        return "approve/add-approve-modal";
    }

    /**
     * 新增审批
     *
     * @return
     */
    @PostMapping("/add")
    public String addApprove(ApproveApply approveApply) {
        approveManageService.addOneApproveApply(approveApply);
        return "redirect:listForEmp";
    }

    /**
     * 部门的审批申请列表（领导看）
     *
     * @param model
     * @return
     */
    @GetMapping("/listForDept")
    public String listForDept(Model model) {
        model.addAttribute("list", approveManageService.listForDept());
        return "approve/listForDept";
    }

    /**
     * 去审批页面
     *
     * @return
     */
    @GetMapping("/toApprove")
    public String toApprove(Long approveApplyId, Model model) {
        model.addAttribute("apply", approveManageService.getOneApproveApply(approveApplyId));
        model.addAttribute("opinionList", approveManageService.listOpinionsForApply(approveApplyId));
        return "approve/approve-modal";
    }

    /**
     * 添加审批意见
     *
     * @param approveOpinion
     * @return
     */
    @PostMapping("/addOpinion")
    public String addOpinion(ApproveOpinion approveOpinion) {
        approveManageService.addOneApproveOpinion(approveOpinion);
        return "redirect:listForDept";
    }

    /**
     * 审批详情页
     *
     * @param approveApplyId
     * @param model
     * @return
     */
    @GetMapping("/detail")
    public String toApproveDetail(Long approveApplyId, Model model) {
        model.addAttribute("apply", approveManageService.getOneApproveApply(approveApplyId));
        model.addAttribute("opinionList", approveManageService.listOpinionsForApply(approveApplyId));
        return "approve/approve-detail-modal";
    }

    /**
     * 完成审批
     *
     * @param approveApplyId
     * @return
     */
    @GetMapping("/complete")
    public String completeApprove(Long approveApplyId) {
        approveManageService.completeApprove(approveApplyId);
        return "redirect:listForDept";
    }

    /**
     * 下载上传的文件
     *
     * @param response
     * @param approveApplyId
     */
    @GetMapping("/download")
    public void downloadApproveFile(HttpServletResponse response, Long approveApplyId) {
        ApproveApply approveApply = approveManageService.getOneApproveApply(approveApplyId);
        Path file = Paths.get(approveApply.getFilePath());
        if (Files.exists(file)) {
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + approveApply.getFileName());
            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
