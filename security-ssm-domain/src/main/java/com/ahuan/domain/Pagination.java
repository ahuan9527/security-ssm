package com.ahuan.domain;

import java.util.List;

public class Pagination<T> {

    //总记录数
    private int total;
    //总页数
    private int pageTotal;

    //每页显示条数
    private int pageSize;
    //当前页
    private int pageNum;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    //当前页商品
    private    List<T> productList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageTotal() {

        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getProductList() {
        return productList;
    }

    public void setProductList(List<T> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "ProductPagination{" +
                "total=" + total +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", productList=" + productList +
                '}';
    }
}
