package org.example.exchangeoffice.controller;

import org.example.exchangeoffice.entity.Currency;
import org.example.exchangeoffice.entity.Employee;
import org.example.exchangeoffice.entity.ExchangeOperation;
import org.example.exchangeoffice.service.CurrencyService;
import org.example.exchangeoffice.service.EmployeeService;
import org.example.exchangeoffice.service.ExchangeOperationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@Controller
@RequestMapping("/exchange")
public class ExchangeController {

    private final EmployeeService employeeService;
    private final CurrencyService currencyService;
    private final ExchangeOperationService exchangeOperationService;

    public ExchangeController(EmployeeService employeeService,
                              CurrencyService currencyService,
                              ExchangeOperationService exchangeOperationService) {
        this.employeeService = employeeService;
        this.currencyService = currencyService;
        this.exchangeOperationService = exchangeOperationService;
    }

    @GetMapping
    public String showExchangeForm(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("currencies", currencyService.getAllCurrencies());
        return "exchange";
    }

    @PostMapping
    public String processExchange(
            @RequestParam(required = false) Long employeeId,
            @RequestParam(required = false) Double amount,
            @RequestParam(required = false) String currencyCode,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            // Проверка входных данных
            if (employeeId == null || currencyCode == null || amount == null) {
                model.addAttribute("message", "Все поля должны быть заполнены.");
                model.addAttribute("employees", employeeService.getAllEmployees());
                model.addAttribute("currencies", currencyService.getAllCurrencies());
                return "exchange";
            }

            if (amount <= 0) {
                model.addAttribute("message", "Сумма обмена должна быть положительной.");
                model.addAttribute("employees", employeeService.getAllEmployees());
                model.addAttribute("currencies", currencyService.getAllCurrencies());
                return "exchange";
            }

            Employee employee = employeeService.getEmployeeById(employeeId);
            Currency currency = currencyService.getCurrencyByCode(currencyCode);

            if (employee == null || currency == null) {
                model.addAttribute("message", "Неверные данные: сотрудник или валюта не найдены.");
                model.addAttribute("employees", employeeService.getAllEmployees());
                model.addAttribute("currencies", currencyService.getAllCurrencies());
                return "exchange";
            }

            // Проверка курса валюты
            double exchangeRateValue = currency.getCurrency();
            if (exchangeRateValue <= 0) {
                model.addAttribute("message", "Курс валюты должен быть положительным.");
                model.addAttribute("employees", employeeService.getAllEmployees());
                model.addAttribute("currencies", currencyService.getAllCurrencies());
                return "exchange";
            }


            BigDecimal amountBigDecimal = BigDecimal.valueOf(amount);
            BigDecimal exchangeRate = BigDecimal.valueOf(exchangeRateValue);
            BigDecimal exchangedAmount = amountBigDecimal.divide(exchangeRate, 2, BigDecimal.ROUND_HALF_UP);


            ExchangeOperation operation = new ExchangeOperation();
            operation.setEmployeeId(employee.getId());
            operation.setCurrencyId(currency.getId());
            operation.setCurrency(currency.getId()); // Заполняем поле currency
            operation.setAmount(amountBigDecimal);
            operation.setRate(exchangeRate);
            operation.setExchangedAmount(exchangedAmount);


            exchangeOperationService.saveExchangeOperation(operation);

            redirectAttributes.addFlashAttribute("message",
                    "Обмен успешно выполнен! Сумма: " + amount + " RUB → " + exchangedAmount + " " + currencyCode);
        } catch (Exception e) {
            model.addAttribute("message", "Произошла ошибка: " + e.getMessage());
            model.addAttribute("employees", employeeService.getAllEmployees());
            model.addAttribute("currencies", currencyService.getAllCurrencies());
            return "exchange";
        }

        return "redirect:/exchange";
    }
}