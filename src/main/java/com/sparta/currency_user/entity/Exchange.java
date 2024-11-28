package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@Table(name = "exchange_requests")
@NoArgsConstructor
public class Exchange extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal amountInKrw;

    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal amountAfterExchange;

    private String status = "normal";

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "currency_id", insertable = false, updatable = false)
    private Currency currency;

    public Exchange(BigDecimal amountInKrw, BigDecimal amountAfterExchange, String status) {
        this.amountInKrw = amountInKrw;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
    }
}
