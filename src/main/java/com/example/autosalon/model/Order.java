package com.example.autosalon.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;

    private Long amount;

    @OneToMany (mappedBy="order", fetch=FetchType.EAGER)
    private Collection<Car> cars;

    @ManyToOne (cascade=CascadeType.ALL)
    private Customer customer;
}
