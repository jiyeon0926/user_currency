package com.sparta.currency_user.service;

import com.sparta.currency_user.controller.dto.CurrencyResponseDto;
import com.sparta.currency_user.controller.dto.ExchangeGroupedResponseDto;
import com.sparta.currency_user.controller.dto.ExchangeResponseDto;
import com.sparta.currency_user.controller.dto.UserResponseDto;
import com.sparta.currency_user.entity.Exchange;
import com.sparta.currency_user.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;
    private final UserService userService;
    private final CurrencyService currencyService;

    @Transactional
    public ExchangeResponseDto save(Long userId, Long currencyId, BigDecimal amountInKrw ) {
        UserResponseDto userDto = userService.findById(userId);
        CurrencyResponseDto currencyDto = currencyService.findById(currencyId);

        Long userDtoId = userDto.getId();
        Long currencyDtoId = currencyDto.getId();
        BigDecimal exchangeRate = currencyDto.getExchangeRate();
        String symbol = currencyDto.getSymbol();

        BigDecimal amountAfterExchange;

        if (symbol.equals("$")) {
            amountAfterExchange = amountInKrw.divide(exchangeRate, 2, RoundingMode.HALF_UP);
        } else {
            amountAfterExchange = amountInKrw.divide(exchangeRate, 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
        }

        Exchange exchange = new Exchange(userDtoId, currencyDtoId, amountInKrw, amountAfterExchange);
        Exchange saved = exchangeRepository.save(exchange);

        return new ExchangeResponseDto(saved);
    }

    public List<ExchangeResponseDto> findAllByUserId(Long userId) {
        return exchangeRepository.findAllByUserIdOrderByModifiedAtDesc(userId).stream().map(ExchangeResponseDto::toDto).toList();
    }

    public ExchangeResponseDto updateStatus(Long userId, Long currencyId, String status) {
        Exchange exchange = exchangeRepository.findExchangeByCurrencyIdOrElseThrow(userId, currencyId);
        exchange.setStatus(status);
        exchangeRepository.save(exchange);

        return new ExchangeResponseDto(exchange);
    }

    public List<ExchangeGroupedResponseDto> findAllGroupedByUserId(Long userId) {
        return exchangeRepository.findAllGroupedByUserId(userId);
    }
}
