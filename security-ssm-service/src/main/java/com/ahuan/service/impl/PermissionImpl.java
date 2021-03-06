package com.ahuan.service.impl;

import com.ahuan.dao.IPermissionDao;
import com.ahuan.domain.Permission;
import com.ahuan.service.IPermissionService;
import com.itheima.ssm.utils.GetUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;
    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        permission.setId(GetUUID.getUUID32());
        permissionDao.save(permission);
    }

    @Override
    public List<Permission> findOtherPermission(String roleId) {
        return permissionDao.findOtherPermission(roleId);
    }
}
