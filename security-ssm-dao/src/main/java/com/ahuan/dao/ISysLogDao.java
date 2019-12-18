package com.ahuan.dao;

import com.ahuan.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISysLogDao {

    @Select("select * from syslog")
    List<SysLog> findAll();

    @Insert("INSERT INTO SYSLOG(visitTime,username,ip, url,executionTime,method) values(#{sysLog.visitTime},#{sysLog.username},#{sysLog.ip},#{sysLog.url},#{sysLog.executionTime},#{sysLog.method})")
    void save(@Param("sysLog") SysLog sysLog);
}
