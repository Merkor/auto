package com.example.autosalon.repositories;

import com.example.autosalon.model.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

    Boolean existsByModel(@NotNull String model);

    Integer deleteCarEntityByModel(@NotNull String model);

    @Query("SELECT c FROM CarEntity c WHERE :isFiltered = FALSE OR LOWER(c.model) LIKE LOWER(CONCAT('%',:filter,'%'))")
    List<CarEntity> findAllByFilter(boolean isFiltered, String filter);
}
