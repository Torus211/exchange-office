package org.example.exchangeoffice.controller;

import org.example.exchangeoffice.entity.Currency;
import org.example.exchangeoffice.service.CurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public String listCurrencies(Model model) {
        model.addAttribute("currencies", currencyService.getAllCurrencies());
        return "currencies";
    }
    @PostMapping
    @ResponseBody
    public Currency saveCurrency(@RequestBody Currency currency) {
        return currencyService.saveCurrency(currency);
    }




}
