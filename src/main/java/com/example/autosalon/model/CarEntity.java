package com.example.autosalon.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    String model;

    @NotNull
    BigDecimal price;

    public static CarEntity makeDefault(String model, BigDecimal price) {
        return builder()
                .model(model)
                .price(price)
                .build();
    }
}
