package com.example.autosalon.controller;

import com.example.autosalon.dto.CustomerDTO;
import com.example.autosalon.exception.BadRequestException;
import com.example.autosalon.factory.CustomerDTOFactory;
import com.example.autosalon.model.CustomerEntity;
import com.example.autosalon.repositories.CustomerRepository;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Controller
@Transactional
@Tag(name = "Контроллер покупателей", description = "CRUD контроллер покупателей")
public class CustomerController {

    CustomerRepository customerRepository;

    CustomerDTOFactory customerDTOFactory;

    public static final String ADD_CUSTOMER = "/api/customer";
    public static final String FETCH_CUSTOMER = "/api/customers";
    public static final String DELETE_CUSTOMER = "/api/customer/{id}";

    @Operation(
            summary = "Добовление покупателя",
            description = "Позволяет добавить нового покупателя"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Новый клиент добавлен"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")})
    @PostMapping(ADD_CUSTOMER)
    public ResponseEntity<CustomerDTO> addCar(@RequestParam String name,
                                              @RequestParam String phoneNumber) {
        if (customerRepository.existsByPhoneNumber(phoneNumber)) {
            throw new BadRequestException(
                    String.format("Покупатель с номером телефона - \"%s\" уже существует", phoneNumber));
        }

        CustomerEntity customerEntity = customerRepository.saveAndFlush(
                CustomerEntity.makeDefault(name, phoneNumber)
        );

        return ResponseEntity.ok(customerDTOFactory.createCustomerDTO(customerEntity));
    }

    @Operation(
            summary = "Список всех покупателей",
            description = "Отдает список всех покупателей"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список клиентов"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")})
    @GetMapping(FETCH_CUSTOMER)
    public ResponseEntity<List<CustomerDTO>> fetchCars() {

        List<CustomerEntity> customers = customerRepository.findAll();

        return ResponseEntity.ok(customerDTOFactory.createCarDTOList(customers));
    }
}
