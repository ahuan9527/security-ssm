package com.ahuan.dao;

import com.ahuan.domain.Permission;
import com.ahuan.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleDao {

    @Select("select * from role where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "rolename"),
            @Result(property = "roleDesc",column = "roledesc")
    }
    )
    Role findRoleByUserId(Long id) throws  Exception;

    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{roleId})")
     List<Permission> findByRoleId(String roleId);

    @Select("select * from role where id in( select roleId from users_role where userId=#{userId})")
    @Results(
            {
                    @Result(id=true,column="id",property="id"),
                    @Result(column="roleName",property="roleName"),
                    @Result(column="roleDesc",property="roleDesc"),
                    @Result(column="id",property="permissions",javaType=List.class,many=@Many(select="com.ahuan.dao.IRoleDao.findByRoleId"))
            })
    List<Role> findByRoleByUserId(String id);


    @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("SELECT * FROM role WHERE  id not in( select roleId from users_role where userId=#{id})")
    List<Role> findOtherRole(String id) throws Exception;

    @Select("SELECT * FROM role where id = #{roleId}")
    List<Role> findById(String roleId) throws Exception;
}
