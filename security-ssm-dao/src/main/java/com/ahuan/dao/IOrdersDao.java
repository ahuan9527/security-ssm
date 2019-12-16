package com.ahuan.dao;

import com.ahuan.domain.Orders;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IOrdersDao {
    List<Orders> selectOrders();
}
