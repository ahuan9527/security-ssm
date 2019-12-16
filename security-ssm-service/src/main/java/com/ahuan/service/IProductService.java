package com.ahuan.service;

import com.ahuan.domain.Pagination;
import com.ahuan.domain.Product;

import java.util.List;


public interface IProductService {
    List<Product> findAll() throws Exception;

    void save(Product product) throws Exception;

    Pagination<Product> findPageHelper(int pageNum, int pageSize) throws Exception;
}
