package com.example.autosalon.controller;
import com.example.autosalon.model.OrdersEntity;
import com.example.autosalon.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional

public class MainController {

    private OrderService orderService;

    public static final String GET_ORDERS = "api/orders";
    public static final String GET_SUM = "api/sum";

    @GetMapping(GET_ORDERS)
    public List<OrdersEntity> getRevenue() {
        return orderService.listOrders();
    }

    @PostMapping(GET_SUM)
    public int getRevenue(@RequestParam("start") String start,
                          @RequestParam("end") String end) {
        return orderService.getOrdersAmountOfPeriod(start, end);
    }
}
