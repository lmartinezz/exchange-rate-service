package com.lmartinez.exchangerate.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "exchange_rate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "origin_currency", nullable = false)
    private String originCurrency;

    @Column(name = "destination_currency", nullable = false)
    private String destinationCurrency;

    @Column(name = "active", nullable = false)
    private Integer active;

    @Column(name = "value")
    private Float value;

}
