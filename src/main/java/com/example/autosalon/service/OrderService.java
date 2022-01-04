package com.example.autosalon.service;

import com.example.autosalon.exception.NotFoundException;
import com.example.autosalon.model.CarEntity;
import com.example.autosalon.repositories.CarRepository;
import com.example.autosalon.repositories.OrdersRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class OrderService {

    CarRepository carRepository;

    OrdersRepository ordersRepository;

    public CarEntity createCarEntity(Long carId) {
        return carRepository.findById(carId)
                            .orElseThrow(() -> new NotFoundException(String.format("Нет автомобиля с id \"%s\"", carId))
                            );
    }

    public List<CarEntity> createCarEntityList(String carsId) {

        return Arrays.stream(carsId.split(","))
                .mapToLong(Long::parseLong)
                .mapToObj(this::createCarEntity)
                .collect(Collectors.toList());
    }

    public BigDecimal getOrdersAmountOfPeriod(LocalDate start, LocalDate end) {
        return ordersRepository.findAllByInstantBetween(start, end)
                .stream()
                .map(a -> a.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
