package com.currency.currencyexchangeservice.jpa;

import com.currency.currencyexchangeservice.bean.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<ExchangeValue, Long> {
}
