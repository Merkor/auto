package com.example.autosalon.dto;

import com.example.autosalon.model.CarEntity;
import com.example.autosalon.model.CustomerEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrdersDTO {

    @NotNull
    Long id;

    @NotNull
    LocalDate instant;

    @NotNull
    BigDecimal amount;

    @NotNull
    Long quantity;

    @NotNull
    List<CarDTO> carDTOList;

    @NotNull
    CustomerDTO customerDTO;
}
