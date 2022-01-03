package com.example.autosalon.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
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
    Instant instant;

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
}
