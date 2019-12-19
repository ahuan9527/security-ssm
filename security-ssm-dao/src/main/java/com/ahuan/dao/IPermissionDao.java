package com.ahuan.dao;

import com.ahuan.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPermissionDao {

    @Select("SELECT * FROM permission")
    List<Permission> findAll() throws  Exception;


    @Insert("insert into permission(id,permissionName,url) values(#{id},#{permissionName},#{url})")
    void save(Permission permission) throws Exception;

    @Select("SELECT * FROM PERMISSION WHERE id not in (SELECT PERMISSIONID FROM ROLE_PERMISSION WHERE ROLEID = #{roleId} )")
    List<Permission> findOtherPermission(String roleId);

    @Select("Select * from PERMISSION where id in (SELECT PERMISSIONID FROM ROLE_PERMISSION WHERE ROLEID = #{roleId})")
    List<Permission> findByRoleId(String roleId);
}
