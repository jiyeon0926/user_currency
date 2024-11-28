package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@Table(name = "currency")
@NoArgsConstructor
public class Currency extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 10, scale = 4, nullable = false)
    private BigDecimal exchangeRate;

    @Column(nullable = false)
    private String currencyName;

    @Column(nullable = false)
    private String symbol;

    public Currency(BigDecimal exchangeRate, String currencyName, String symbol) {
        this.exchangeRate = exchangeRate;
        this.currencyName = currencyName;
        this.symbol = symbol;
    }
}
