package com.ahuan.service.impl;

import com.ahuan.dao.IOrdersDao;
import com.ahuan.domain.Orders;
import com.ahuan.domain.Pagination;
import com.ahuan.domain.Product;
import com.ahuan.service.IOrdersService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements IOrdersService {
    private Logger logger = Logger.getLogger(OrdersServiceImpl.class);

    @Autowired
    private IOrdersDao iOrdersDao;
    @Override
    public Pagination<Orders> selectOrders(String pageNum, String pageSize) {
        logger.info("===== 开始进行分页处理！ ========");
        PageHelper.startPage(Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        List<Orders> orders = iOrdersDao.selectOrders();
        System.out.println("查询订单数:"+orders.size());
        for (Orders orders1:orders){
            System.out.println(orders1);
        }
        PageInfo<Orders> pageInfo = new PageInfo<>(orders);
        Pagination<Orders> pagination = new Pagination<>();
        pagination.setPageNum(Integer.valueOf(pageNum));
        pagination.setPageSize(Integer.valueOf(pageSize));
        pagination.setPageTotal((int)pageInfo.getTotal()%Integer.valueOf(pageSize)!=0?(int)pageInfo.getTotal()/Integer.valueOf(pageSize)+1:(int)pageInfo.getTotal()/Integer.valueOf(pageSize));
        pagination.setProductList(orders);
        logger.info("===== 结束进行分页处理！ ========");

        return pagination;
    }
}
