package com.ahuan.service;

import com.ahuan.domain.SysLog;

import java.util.List;

public interface ISysLogService {
    List<SysLog> findAll();

    void save(SysLog sysLog);
}
