package com.longder.gov.controller;

import com.longder.gov.entity.po.ApproveApply;
import com.longder.gov.entity.po.DocInfo;
import com.longder.gov.entity.po.SysUser;
import com.longder.gov.security.SecurityUtil;
import com.longder.gov.service.DocInfoMangeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文档管理相关控制器
 */
@Controller
@RequestMapping("/admin/doc")
public class DocManageController {

    @Resource
    private DocInfoMangeService docInfoMangeService;

    /**
     * 部门下的文档列表
     * @return
     */
    @GetMapping("/list")
    public String listForDept(Model model){
        SysUser currentUser = SecurityUtil.getCurrentUser();
        assert currentUser != null;
        model.addAttribute("list",docInfoMangeService.listForDept(currentUser.getDepartment().getId()));
        return "doc/list";
    }

    /**
     * 去添加文档
     * @return
     */
    @GetMapping("/toAdd")
    public String toAdd(){
        return "doc/add-doc-modal";
    }

    /**
     * 添加文档
     * @return
     */
    @PostMapping("/add")
    public String add(DocInfo docInfo){
        docInfoMangeService.addOneDocInfo(docInfo);
        return "redirect:list";
    }
    /**
     * 下载上传的文件
     *
     * @param response
     */
    @GetMapping("/download")
    public void downloadDoc(HttpServletResponse response, Long docInfoId) {
        DocInfo docInfo = docInfoMangeService.getOneDoc(docInfoId);
        Path file = Paths.get(docInfo.getPath());
        if (Files.exists(file)) {
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + docInfo.getFileName());
            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
