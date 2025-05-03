package org.example.exchangeoffice.service;

import org.example.exchangeoffice.entity.Currency;
import org.example.exchangeoffice.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {
    private final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    public Currency getCurrencyByCode(String code) {
        return currencyRepository.findByCode(code);
    }

    public Currency saveCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }


}
