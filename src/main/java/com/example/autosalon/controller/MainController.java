package com.example.autosalon.controller;
import com.example.autosalon.model.Orders;
import com.example.autosalon.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    private final OrderService orderService;

    @Autowired
    public MainController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<Orders> getRevenue() {
        return orderService.listOrders();
    }

    @Operation(
            summary = "Возвращает справочник стран",
            description = "Возвращает справочник стран."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Справочник стран"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")})
    @GetMapping("/orders")
    @PostMapping("/sum")
    public int getRevenue(@RequestParam("start") String start,
                          @RequestParam("end") String end) {
        return orderService.getOrdersAmountOfPeriod(start, end);
    }
}
