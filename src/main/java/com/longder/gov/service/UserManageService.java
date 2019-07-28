package com.longder.gov.service;

import com.longder.gov.entity.po.SysUser;

import java.util.List;

public interface UserManageService {

    /**
     * 添加一个用户，包括它的角色，部门
     */
    void addOneUser(SysUser sysUser);

    /**
     * 查询所有用户
     * @return
     */
    List<SysUser> listAll();
}
