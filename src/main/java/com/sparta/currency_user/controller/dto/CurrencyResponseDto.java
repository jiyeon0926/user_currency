package com.sparta.currency_user.controller.dto;

import com.sparta.currency_user.entity.Currency;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class CurrencyResponseDto {
    private Long id;
    private String currencyName;
    private BigDecimal exchangeRate;
    private String symbol;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CurrencyResponseDto(Currency currency) {
        this.id = currency.getId();
        this.exchangeRate = currency.getExchangeRate();
        this.currencyName = currency.getCurrencyName();
        this.symbol = currency.getSymbol();
        this.createdAt = currency.getCreatedAt();
        this.modifiedAt = currency.getModifiedAt();
    }

    public CurrencyResponseDto(Long id, BigDecimal exchangeRate, String currencyName, String symbol, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.exchangeRate = exchangeRate;
        this.currencyName = currencyName;
        this.symbol = symbol;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static CurrencyResponseDto toDto(Currency currency) {
        return new CurrencyResponseDto(
            currency.getId(),
            currency.getExchangeRate(),
            currency.getCurrencyName(),
            currency.getSymbol(),
            currency.getCreatedAt(),
            currency.getModifiedAt()
        );
    }
}
