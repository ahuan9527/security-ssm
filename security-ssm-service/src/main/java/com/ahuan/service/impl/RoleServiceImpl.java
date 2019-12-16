package com.ahuan.service.impl;

import com.ahuan.dao.IRoleDao;
import com.ahuan.domain.Role;
import com.ahuan.exception.BusinessException;
import com.ahuan.exception.ResultEnum;
import com.ahuan.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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
    public List<Role> findById(String roleId) throws Exception {
        return roleDao.findById(roleId);
    }
}
