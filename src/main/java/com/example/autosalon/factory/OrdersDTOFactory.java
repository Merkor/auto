package com.example.autosalon.factory;

import com.example.autosalon.dto.OrdersDTO;
import com.example.autosalon.model.OrdersEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrdersDTOFactory {

    CarDTOFactory carDTOFactory;

    CustomerDTOFactory customerDTOFactory;

    Comparator<OrdersDTO> comparing = Comparator.comparing(OrdersDTO::getInstant)
            .thenComparing(Comparator.comparing(a -> a.getCustomerDTO().getName()))
            .thenComparing(Comparator.comparing(OrdersDTO::getAmount).reversed());

    public OrdersDTO createOrdersDTO(OrdersEntity entity) {
        return OrdersDTO
                .builder()
                .id(entity.getId())
                .instant(entity.getInstant())
                .amount(entity.getAmount())
                .quantity(entity.getQuantity())
                .carDTOList(carDTOFactory.createCarDTOList(entity.getCarEntity()))
                .customerDTO(customerDTOFactory.createCustomerDTO(entity.getCustomerEntity()))
                .build();
    }

    public List<OrdersDTO> createOrdersDTOList(List<OrdersEntity> entityList) {
        return entityList
                .stream()
                .map(this::createOrdersDTO)
                .collect(Collectors.toList());
    }

    public LinkedList<OrdersDTO> createSortedOrdersDTOList(List<OrdersEntity> entityList) {
        return createOrdersDTOList(entityList)
                .stream()
                .sorted(comparing)
                .collect(Collectors
                .toCollection(LinkedList::new));
    }
}
