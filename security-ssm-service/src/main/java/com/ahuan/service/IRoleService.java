package com.ahuan.service;

import com.ahuan.domain.Role;

import java.util.List;

public interface IRoleService {
   List<Role> findAll() throws Exception;
   void save(Role role) throws Exception;

    List<Role> findOtherRole(String id) throws Exception;

    List<Role> findById(String roleId) throws Exception;

    void addPermissionToRole(String roleId, String[] ids);
}
