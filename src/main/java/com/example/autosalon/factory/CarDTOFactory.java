package com.example.autosalon.factory;

import com.example.autosalon.dto.CarDTO;
import com.example.autosalon.model.CarEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarDTOFactory {

    public CarDTO createCarDTO(CarEntity entity) {
        return CarDTO
                .builder()
                .id(entity.getId())
                .model(entity.getModel())
                .price(entity.getPrice())
                .build();
    }

    public List<CarDTO> createCarDTOList(List<CarEntity> entityList) {
        return entityList
                .stream()
                .map(this::createCarDTO)
                .collect(Collectors.toList());
    }
}
