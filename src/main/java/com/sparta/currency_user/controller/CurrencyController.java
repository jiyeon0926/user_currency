package com.sparta.currency_user.controller;

import com.sparta.currency_user.controller.dto.CurrencyRequestDto;
import com.sparta.currency_user.controller.dto.CurrencyResponseDto;
import com.sparta.currency_user.service.CurrencyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/currencies")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    // 통화 전체 조회
    @GetMapping
    public ResponseEntity<List<CurrencyResponseDto>> findCurrencies() {
        return ResponseEntity.ok().body(currencyService.findAll());
    }

    // 통화 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<CurrencyResponseDto> findCurrency(@PathVariable Long id) {
        return ResponseEntity.ok().body(currencyService.findById(id));
    }

    // 통화 등록
    @PostMapping
    public ResponseEntity<CurrencyResponseDto> createCurrency(@Valid @RequestBody CurrencyRequestDto currencyRequestDto) {
        BigDecimal exchangeRate = currencyRequestDto.getExchangeRate();

        if (exchangeRate == null || exchangeRate.compareTo(BigDecimal.ZERO) <= 0) {
            log.error("유효하지 않은 값 = {}", exchangeRate);
            throw new IllegalArgumentException("0이나 음수는 유효하지 않은 값입니다.");
        }

        return ResponseEntity.ok().body(currencyService.save(currencyRequestDto));
    }
}
