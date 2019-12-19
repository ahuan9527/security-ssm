package com.ahuan.service.impl;

import com.ahuan.dao.ISysLogDao;
import com.ahuan.domain.SysLog;
import com.ahuan.service.ISysLogService;
import com.itheima.ssm.utils.GetUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogService implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;
    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }

    @Override
    public void save(SysLog sysLog) {
        sysLog.setId(GetUUID.getUUID32());
        sysLogDao.save(sysLog);
    }
}
