package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/*
- userId와 currencyId는 저장 용도
- user와 currency는 1:N 양방향을 위한 용도
 */
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

    @Setter
    private String status = "normal";

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "currency_id")
    private Long currencyId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "currency_id", insertable = false, updatable = false)
    private Currency currency;

    public Exchange(Long userId, Long currencyId, BigDecimal amountInKrw, BigDecimal amountAfterExchange) {
        this.userId = userId;
        this.currencyId = currencyId;
        this.amountInKrw = amountInKrw;
        this.amountAfterExchange = amountAfterExchange;
    }
}
