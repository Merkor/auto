package com.example.autosalon.factory;

import com.example.autosalon.dto.CustomerDTO;
import com.example.autosalon.model.CustomerEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerDTOFactory {

    public CustomerDTO createCustomerDTO(CustomerEntity entity) {
        return CustomerDTO
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .phoneNumber(entity.getPhoneNumber())
                .build();
    }

    public List<CustomerDTO> createCarDTOList(List<CustomerEntity> entityList) {
        return entityList
                .stream()
                .map(this::createCustomerDTO)
                .collect(Collectors.toList());
    }
}
