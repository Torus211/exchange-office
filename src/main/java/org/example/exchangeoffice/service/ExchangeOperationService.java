package org.example.exchangeoffice.service;

import org.example.exchangeoffice.entity.ExchangeOperation;

import org.example.exchangeoffice.repository.ExchangeOperationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExchangeOperationService {

    private final ExchangeOperationRepository exchangeOperationRepository;

    public ExchangeOperationService(ExchangeOperationRepository exchangeOperationRepository) {
        this.exchangeOperationRepository = exchangeOperationRepository;
    }

    public List<ExchangeOperation> getAllExchangeOperations() {
        return exchangeOperationRepository.findAll();
    }

    public ExchangeOperation saveExchangeOperation(ExchangeOperation exchangeOperation) {
        return exchangeOperationRepository.save(exchangeOperation);
    }
}
