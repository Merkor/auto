package com.example.autosalon.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class OrdersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    LocalDate instant;

    @NotNull
    BigDecimal amount;

    @NotNull
    Long quantity;

    @NotNull
    @ManyToMany
    List<CarEntity> carEntity;

    @NotNull
    @ManyToOne
    CustomerEntity customerEntity;

    public static OrdersEntity makeDefault(List<CarEntity> carEntities, CustomerEntity customerEntity) {

        return OrdersEntity.builder()
                .instant(LocalDate.now())
                .amount(carEntities.stream().map(car -> car.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add))
                .quantity(carEntities.stream().count())
                .carEntity(carEntities)
                .customerEntity(customerEntity)
                .build();
    }
}
