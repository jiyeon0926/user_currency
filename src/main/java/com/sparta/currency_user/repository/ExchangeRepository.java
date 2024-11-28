package com.sparta.currency_user.repository;

import com.sparta.currency_user.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

    List<Exchange> findAllByUserIdOrderByModifiedAt(Long userId);

    Optional<Exchange> findExchangeByCurrencyId(Long currencyId);

    default Exchange findExchangeByCurrencyIdOrElseThrow(Long currencyId) {
        return findExchangeByCurrencyId(currencyId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "통화를 찾을 수 없습니다."));
    }
}
