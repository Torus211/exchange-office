package org.example.exchangeoffice.repository;

import org.example.exchangeoffice.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Currency findByCode(String code);
}
