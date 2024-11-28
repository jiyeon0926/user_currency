package com.sparta.currency_user.controller;

import com.sparta.currency_user.controller.dto.ExchangeResponseDto;
import com.sparta.currency_user.controller.dto.ExchangeSaveRequestDto;
import com.sparta.currency_user.controller.dto.ExchangeUpdateRequestDto;
import com.sparta.currency_user.service.ExchangeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchanges")
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    // 환전 요청 등록
    @PostMapping
    public ResponseEntity<ExchangeResponseDto> createExchange(@Valid @RequestBody ExchangeSaveRequestDto exchangeSaveRequestDto) {
        return ResponseEntity.ok().body(exchangeService.save(exchangeSaveRequestDto.getUserId(), exchangeSaveRequestDto.getCurrencyId(), exchangeSaveRequestDto.getAmountInKrw()));
    }

    // 특정 고객이 수행한 환전 요청 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<ExchangeResponseDto>> findExchange(@PathVariable Long userId) {
        return ResponseEntity.ok().body(exchangeService.findAllByUserId(userId));
    }

    // 특정 환전 요청 상태를 취소로 변경
    @PatchMapping("/{userId}")
    public ResponseEntity<ExchangeResponseDto> updateStatus(@PathVariable Long userId, @RequestBody ExchangeUpdateRequestDto exchangeUpdateRequestDto) {
        return ResponseEntity.ok().body(exchangeService.updateStatus(exchangeUpdateRequestDto.getCurrencyId(), exchangeUpdateRequestDto.getStatus()));
    }
}
