package com.example.autosalon.service;

import com.example.autosalon.dao.OrderDao;
import com.example.autosalon.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    private OrderDao orderDao;

    @Autowired
    public void setUserDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public List<Order> listOrders() {
       return orderDao.listOrders();
    }

    public int getOrdersAmountOfPeriod(Date start, Date end) {
        return orderDao.getOrdersAmountOfPeriod(start, end);
    }

}
