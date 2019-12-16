package com.ahuan.domain;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Orders {

    private String id;
    private String orderNum;
    private Date orderTime;

    private String orderTimeStr;
    private int orderStatus;
    private int peopleCount;
    private Product product;
    private List<Traveller> travellers;

    private Member member;
    private Integer payType;
    private String payTypeStr;
    private String orderDesc;


}
