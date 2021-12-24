package com.example.autosalon.service;

import com.example.autosalon.dao.OrderDao;
import com.example.autosalon.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    private OrderDao orderDao;

    @Autowired
    public void setUserDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public List<Orders> listOrders() {
       return orderDao.listOrders();
    }

    public int getOrdersAmountOfPeriod(String ins1, String ins2) {
        return orderDao.getOrdersAmountOfPeriod(ins1, ins2);
    }

}
