package com.example.autosalon.service;

import com.example.autosalon.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderDao orderDao;

    @Autowired
    public void setUserDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }



}
