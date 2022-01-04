package com.example.autosalon.repositories;

import com.example.autosalon.model.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, Long> {
    List<OrdersEntity> findAllByInstantBetween(@NotNull LocalDate instant, @NotNull LocalDate instant2);
}
