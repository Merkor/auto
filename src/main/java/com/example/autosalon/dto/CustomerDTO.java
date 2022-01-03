package com.example.autosalon.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerDTO {

    @NotNull
    Long id;

    @NotNull
    String name;

    @NotNull
    String phoneNumber;
}
