package com.example.autosalon.repositories;

import com.example.autosalon.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.validation.constraints.NotNull;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    Boolean existsByPhoneNumber(@NotNull String phoneNumber);
}
