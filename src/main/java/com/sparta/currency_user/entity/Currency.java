package com.sparta.currency_user.entity;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "currency_id")
    private List<Exchange> exchanges = new ArrayList<>();

    public Currency(BigDecimal exchangeRate, String currencyName, String symbol) {
        this.exchangeRate = exchangeRate;
        this.currencyName = currencyName;
        this.symbol = symbol;
    }

    @PostConstruct
    public void initExchangeRate() {
    }
}
