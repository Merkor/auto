package com.example.autosalon.controller;
import com.example.autosalon.model.Orders;
import com.example.autosalon.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    private OrderService orderService;

    @Autowired
    public MainController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<Orders> getRevenue() {
        return orderService.listOrders();
    }

    @PostMapping("/sum")
    public int getRevenue(@RequestParam("start") String start,
                          @RequestParam("end") String end) {
        return orderService.getOrdersAmountOfPeriod(start, end);
    }
}
