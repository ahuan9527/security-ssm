package com.itheima.ssm.utils;

import java.util.UUID;

public class GetUUID {
    //获取32位得UUID
    public static String getUUID32(){
        return UUID.randomUUID().toString().replace("-","").toLowerCase();
    }
}
