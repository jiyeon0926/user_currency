package com.sparta.currency_user.controller;

import com.sparta.currency_user.controller.dto.ExchangeResponseDto;
import com.sparta.currency_user.controller.dto.ExchangeSaveRequestDto;
import com.sparta.currency_user.controller.dto.ExchangeGroupedResponseDto;
import com.sparta.currency_user.controller.dto.ExchangeUpdateRequestDto;
import com.sparta.currency_user.service.ExchangeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        String status = exchangeUpdateRequestDto.getStatus();

        if (!status.equals("canceled")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "currencyId만 요청하거나 'canceled'라고 정확하게 입력해야 합니다.");
        }

        return ResponseEntity.ok().body(exchangeService.updateStatus(userId, exchangeUpdateRequestDto.getCurrencyId(), status));
    }

    // 특정 고객의 모든 환전 요청을 그룹화하여 조회
    @GetMapping("/{userId}/grouped")
    public ResponseEntity<List<ExchangeGroupedResponseDto>> findAllGroupedByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok().body(exchangeService.findAllGroupedByUserId(userId));
    }
}
