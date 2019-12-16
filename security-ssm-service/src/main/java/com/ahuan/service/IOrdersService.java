package com.ahuan.service;

import com.ahuan.domain.Orders;
import com.ahuan.domain.Pagination;

public interface IOrdersService {
    //分页查询
    Pagination<Orders> selectOrders(String pageNum,String pageSize);
}
