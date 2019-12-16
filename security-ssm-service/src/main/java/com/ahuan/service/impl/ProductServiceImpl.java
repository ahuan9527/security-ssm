package com.ahuan.service.impl;


import com.ahuan.dao.IProductDao;
import com.ahuan.domain.Pagination;
import com.ahuan.domain.Product;
import com.ahuan.service.IProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.ssm.utils.GetUUID;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao iProductDao;

    private Logger logger = Logger.getLogger(ProductServiceImpl.class);


    @Override
    public List<Product> findAll() throws Exception {
        List<Product> all = iProductDao.findAll();
        if (StringUtils.isEmpty(all)){
            throw new RuntimeException("产品查询为NULL！");
        }
        logger.info(all);
        return all;
    }

    @Override
    public void save(Product product) throws Exception {
        logger.debug("进入service");
        product.setId(GetUUID.getUUID32());
        System.out.println(product);
        iProductDao.save(product);
    }

    @Override
    public Pagination<Product> findPageHelper(int pageNum, int pageSize) throws Exception {
        logger.info( "开始进行分页查询！");
        PageHelper.startPage(pageNum, pageSize);
        List<Product> products = iProductDao.findAll();
        for (Product product:products){
            System.out.println(product);
        }
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        //总数
        long total = pageInfo.getTotal();
        System.out.println("总记录数："+total);
        Pagination<Product> productPagination = new Pagination();
        productPagination.setPageSize(pageSize);
        productPagination.setTotal((int) total);
        productPagination.setProductList(products);
        productPagination.setPageNum(pageNum);
        productPagination.setPageTotal((int)total%pageSize!=0?(int)total/pageSize+1:(int)total/pageSize);

        System.out.println("总页数:"+productPagination.getPageTotal()+",当前页："+pageNum);
        logger.info("分页查询结束！");
        return productPagination;
    }


}
