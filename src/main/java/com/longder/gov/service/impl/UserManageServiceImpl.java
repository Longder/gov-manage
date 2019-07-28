package com.longder.gov.service.impl;

import com.longder.gov.entity.po.SysUser;
import com.longder.gov.entity.po.SysUserRole;
import com.longder.gov.repository.SysUserRepository;
import com.longder.gov.repository.SysUserRoleRepository;
import com.longder.gov.service.UserManageService;
import com.longder.gov.util.EncryptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class UserManageServiceImpl implements UserManageService {

    @Resource
    private SysUserRepository sysUserRepository;
    @Resource
    private SysUserRoleRepository sysUserRoleRepository;
    /**
     * 默认密码
     */
    @Value("${system.default-password}")
    private String defaultPassword;

    /**
     * 添加一个用户，包括它的角色，部门
     *
     * @param sysUser
     */
    @Override
    @Transactional
    public void addOneUser(SysUser sysUser) {
        //密码设置为默认密码
        sysUser.setPassword(EncryptionUtil.encrypt(defaultPassword));
        sysUserRepository.save(sysUser);
        SysUserRole userRole = new SysUserRole();
        userRole.setSysUser(sysUser);
        userRole.setRole(sysUser.getRole());
        sysUserRoleRepository.save(userRole);
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @Override
    public List<SysUser> listAll() {
        List<SysUser> userList = sysUserRepository.findAll();
        //循环 set角色
        userList.forEach(sysUser -> sysUser.setRole(sysUser.getRoles().get(0).getRole()));
        //移除管理员
        userList.removeIf(sysUser -> ObjectUtils.isEmpty(sysUser.getDepartment()));
        return userList;
    }
}
