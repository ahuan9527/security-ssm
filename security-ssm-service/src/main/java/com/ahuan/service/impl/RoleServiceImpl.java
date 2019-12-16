package com.ahuan.service.impl;

import com.ahuan.dao.IRoleDao;
import com.ahuan.domain.Role;
import com.ahuan.exception.BusinessException;
import com.ahuan.exception.ResultEnum;
import com.ahuan.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;
    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        if (StringUtils.isEmpty(role)){
            throw new BusinessException(ResultEnum.NOTNULL_ERROR);
        }
        roleDao.save(role);
    }

    @Override
    public List<Role> findOtherRole(String id) throws Exception {
        return roleDao.findOtherRole(id);
    }

    @Override
    public Role findById(String roleId) throws Exception {
        return roleDao.findById(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] ids) {
        if (StringUtils.isEmpty(roleId) || ids.length < 0){
            throw new BusinessException(ResultEnum.NOTNULL_ERROR);
        }
        Arrays.stream(ids).filter(p->{
            roleDao.addPermissionToRole(roleId,p);
            return true;
        }).collect(Collectors.toList());
    }

    @Override
    public Role findByIdOne(String roleId) {
        return roleDao.findByIdOne(roleId);
    }

}
