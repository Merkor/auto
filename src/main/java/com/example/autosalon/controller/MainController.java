package com.example.autosalon.controller;
import com.example.autosalon.model.Order;
import com.example.autosalon.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
public class MainController {

    private OrderService orderService;

    @Autowired
    public MainController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public List<Order> getRevenue(Model model) {
        return orderService.listOrders();
    }

    @PostMapping("/sum")
    public int getRevenue(Model model,
                                  @RequestParam("start") Date start,
                                  @RequestParam("end") Date end) {
        return orderService.getOrdersAmountOfPeriod(start, end);
    }
}
