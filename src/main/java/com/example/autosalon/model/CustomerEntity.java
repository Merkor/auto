package com.example.autosalon.model;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    String name;

    @NotNull
    String phoneNumber;

    public static CustomerEntity makeDefault(String name, String phoneNumber) {
        return builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .build();
    }
}
