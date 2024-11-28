package com.sparta.currency_user.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ExchangeUpdateRequestDto {

    @NotNull(message = "통화 아이디는 필수 입력 항목입니다.")
    private Long currencyId;

    private String status = "canceled";
}
