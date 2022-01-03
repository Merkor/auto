package com.example.autosalon.dao;
import com.example.autosalon.model.OrdersEntity;

import java.util.List;

public interface OrderDao {

    List<OrdersEntity> listOrders();

    int getOrdersAmountOfPeriod(String ins1, String ins2);

}
