package com.sparta.currency_user.repository;

import com.sparta.currency_user.controller.dto.ExchangeGroupedResponseDto;
import com.sparta.currency_user.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

    List<Exchange> findAllByUserIdOrderByModifiedAtDesc(Long userId);

    Optional<Exchange> findExchangeByUserIdAndCurrencyId(Long userId, Long currencyId);

    default Exchange findExchangeByCurrencyIdOrElseThrow(Long userId, Long currencyId) {
        return findExchangeByUserIdAndCurrencyId(userId, currencyId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "고객의 환전 요청 내역을 찾을 수 없습니다."));
    }

    @Query("SELECT new com.sparta.currency_user.controller.dto.ExchangeGroupedResponseDto(COUNT(e.amountInKrw), SUM(e.amountInKrw)) FROM Exchange e WHERE e.status = 'normal' AND e.userId = :userId GROUP BY e.userId")
    List<ExchangeGroupedResponseDto> findAllGroupedByUserId(@Param("userId") long userId);
}
