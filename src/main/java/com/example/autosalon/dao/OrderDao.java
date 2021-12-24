package com.example.autosalon.dao;
import com.example.autosalon.model.Orders;

import java.util.List;

public interface OrderDao {

    List<Orders> listOrders();

    int getOrdersAmountOfPeriod(String ins1, String ins2);

}
