package com.example.autosalon.dao;
import com.example.autosalon.model.Order;

import java.util.Date;
import java.util.List;

public interface OrderDao {

    List<Order> listOrders();

    int getOrdersAmountOfPeriod(Date start, Date end);

}
