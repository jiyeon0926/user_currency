package com.sparta.currency_user.controller.dto;

import com.sparta.currency_user.entity.Currency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CurrencyRequestDto {

    @NotNull(message = "환율은 필수 입력 항목입니다.")
    private BigDecimal exchangeRate;

    @NotBlank(message = "통화 이름은 필수 입력 항목입니다.")
    private String currencyName;

    @NotBlank(message = "표시는 필수 입력 항목입니다.")
    private String symbol;

    public Currency toEntity() {
        return new Currency(
                this.exchangeRate,
                this.currencyName,
                this.symbol
        );
    }
}
