package com.sparta.currency_user.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ExchangeGroupedResponseDto {

    private Long count;
    private BigDecimal totalAmountInKrw;
}
