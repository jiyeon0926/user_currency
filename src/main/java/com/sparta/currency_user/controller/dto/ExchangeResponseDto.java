package com.sparta.currency_user.controller.dto;

import com.sparta.currency_user.entity.Exchange;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class ExchangeResponseDto {

    private Long id;
    private Long userId;
    private Long currencyId;
    private BigDecimal amountInKrw;
    private BigDecimal amountAfterExchange;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ExchangeResponseDto(Exchange exchange) {
        this.id = exchange.getId();
        this.userId = exchange.getUserId();
        this.currencyId = exchange.getCurrencyId();
        this.amountInKrw = exchange.getAmountInKrw();
        this.amountAfterExchange = exchange.getAmountAfterExchange();
        this.status = exchange.getStatus();
        this.createdAt = exchange.getCreatedAt();
        this.modifiedAt = exchange.getModifiedAt();
    }

    public ExchangeResponseDto(Long id, Long userId, Long currencyId, BigDecimal amountInKrw, BigDecimal amountAfterExchange, String status, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userId = userId;
        this.currencyId = currencyId;
        this.amountInKrw = amountInKrw;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static ExchangeResponseDto toDto(Exchange exchange) {
        return new ExchangeResponseDto(
                exchange.getId(),
                exchange.getUserId(),
                exchange.getCurrencyId(),
                exchange.getAmountInKrw(),
                exchange.getAmountAfterExchange(),
                exchange.getStatus(),
                exchange.getCreatedAt(),
                exchange.getModifiedAt()
        );
    }
}
