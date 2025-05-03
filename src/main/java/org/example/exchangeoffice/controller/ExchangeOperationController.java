package org.example.exchangeoffice.controller;

import org.example.exchangeoffice.service.ExchangeOperationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/operations")
public class ExchangeOperationController {

    private final ExchangeOperationService exchangeOperationService;

    public ExchangeOperationController(ExchangeOperationService exchangeOperationService) {
        this.exchangeOperationService = exchangeOperationService;
    }

    @GetMapping
    public String listOperations(Model model) {
        model.addAttribute("exchangeOperations", exchangeOperationService.getAllExchangeOperations());
        return "exchange_operations";
    }
}
