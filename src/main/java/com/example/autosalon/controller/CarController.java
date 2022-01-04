package com.example.autosalon.controller;

import com.example.autosalon.dto.AckDTO;
import com.example.autosalon.dto.CarDTO;
import com.example.autosalon.exception.BadRequestException;
import com.example.autosalon.factory.CarDTOFactory;
import com.example.autosalon.model.CarEntity;
import com.example.autosalon.repositories.CarRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Controller
@Transactional
@Tag(name="Контроллер автомобилей", description="CRUD контроллер атомобилей")
public class CarController {

    CarRepository carRepository;

    CarDTOFactory carDTOFactory;

    public static final String ADD_CAR = "/api/cars";
    public static final String FETCH_CAR = "/api/cars";
    public static final String DELETE_CAR = "/api/cars/{model}";

    @Operation(
            summary = "Добовление атомобиля",
            description = "Позволяет добавить новую модель автомобиля"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Новая модель добавлена"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")})
    @PostMapping(ADD_CAR)
    public ResponseEntity<CarDTO> addCar(@RequestParam String model,
                                         @RequestParam BigDecimal price) {
        if (carRepository.existsByModel(model)) {
            throw new BadRequestException(String.format("Модель \"%s\" уже существует", model));
        }

        CarEntity carEntity = carRepository.saveAndFlush(
                CarEntity.makeDefault(model, price)
        );

        return ResponseEntity.ok(carDTOFactory.createCarDTO(carEntity));
    }

    @Operation(
            summary = "Список всех моделей",
            description = "Отдает список всех моделей автомобилей"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список моделей"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")})
    @GetMapping(FETCH_CAR)
    public ResponseEntity<List<CarDTO>> fetchCars(@RequestParam(defaultValue = "") String filter) {

        boolean isFiltered = !filter.trim().isEmpty();

        List<CarEntity> cars = carRepository.findAllByFilter(isFiltered, filter);

        return ResponseEntity.ok(carDTOFactory.createCarDTOList(cars));
    }

    @Operation(
            summary = "Удаление автомобиля",
            description = "Удаляет автомобиль по модели"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Автомобиль удален"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")})
    @DeleteMapping(DELETE_CAR)
    public ResponseEntity<AckDTO> deleteCar(@PathVariable String model) {

        if (carRepository.existsByModel(model)) {
            carRepository.deleteCarEntityByModel(model);
        }
        return ResponseEntity.ok(AckDTO.makeDefault(true));
    }
}
