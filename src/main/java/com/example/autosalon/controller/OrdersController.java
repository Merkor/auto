package com.example.autosalon.controller;

import com.example.autosalon.dto.OrdersDTO;
import com.example.autosalon.factory.OrdersDTOFactory;
import com.example.autosalon.model.OrdersEntity;
import com.example.autosalon.repositories.CustomerRepository;
import com.example.autosalon.repositories.OrdersRepository;
import com.example.autosalon.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Controller
@Transactional
@Tag(name="Контроллер заказов", description="CRUD контроллер заказов")
public class OrdersController {

    OrderService orderService;

    OrdersRepository ordersRepository;

    CustomerRepository customerRepository;

    OrdersDTOFactory ordersDTOFactory;

    public static final String ADD_ORDER = "/api/orders";
    public static final String FETCH_ORDER = "/api/orders";
    public static final String FETCH_ORDER1 = "/api/orders/sorted";
    public static final String GET_SUM = "api/orders/sum/{start}/{end}";

    @Operation(
            summary = "Добовление заказа",
            description = "Позволяет добавить новый заказ"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Новый заказ добавлен"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")})
    @PostMapping(ADD_ORDER)
    public ResponseEntity<OrdersDTO> addOrder(@RequestParam Long customerId,
                                              @RequestParam String carsId) {

        OrdersEntity ordersEntity = ordersRepository.saveAndFlush(
                OrdersEntity.makeDefault(orderService.createCarEntityList(carsId),
                        customerRepository.getById(customerId))
        );

        return ResponseEntity.ok(ordersDTOFactory.createOrdersDTO(ordersEntity));
    }

    @Operation(
            summary = "Список всех заказов",
            description = "Отдает список всех заказов"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список заказов"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")})
    @GetMapping(FETCH_ORDER)
    public ResponseEntity<List<OrdersDTO>> fetchOrders() {

        List<OrdersEntity> orders = ordersRepository.findAll();

        return ResponseEntity.ok(ordersDTOFactory.createOrdersDTOList(orders));
    }

    @Operation(
            summary = "Отсортированный список всех заказов",
            description = "Отдает отсортированный список всех заказов"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Отсортированный список заказов"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")})
    @GetMapping(FETCH_ORDER1)
    public ResponseEntity<List<OrdersDTO>> fetchSortedOrders() {

        List<OrdersEntity> orders = ordersRepository.findAll();

        return ResponseEntity.ok(ordersDTOFactory.createSortedOrdersDTOList(orders));
    }

    @Operation(
            summary = "Выручка за период",
            description = "Выдает сумму выручки за период"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Сумма выручки за период"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")})
    @GetMapping(GET_SUM)
    public ResponseEntity<BigDecimal> getRevenue(@PathVariable String start,
                                                 @PathVariable String end) {
        return ResponseEntity.ok(orderService.getOrdersAmountOfPeriod(LocalDate.parse(start), LocalDate.parse(end)));
    }
}
