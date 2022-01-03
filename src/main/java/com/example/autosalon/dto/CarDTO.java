package com.example.autosalon.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarDTO {

    @NotNull
    Long id;

    @NotNull
    String model;

    @NotNull
    BigDecimal price;
}
