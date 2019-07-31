package com.longder.gov.service;

import com.longder.gov.entity.po.DocInfo;

import java.util.List;

public interface DocInfoMangeService {

    /**
     * 查询部门下的所有文档
     * @param deptId
     * @return
     */
    List<DocInfo> listForDept(Long deptId);

    /**
     * 添加一个文档
     */
    void addOneDocInfo(DocInfo docInfo);

    DocInfo getOneDoc(Long docInfoId);
}
