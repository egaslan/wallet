package com.joinzad.wallet.currency.conversion.controllers;

import com.joinzad.wallet.currency.conversion.exchange.dto.ExchangeResponseDTO;
import com.joinzad.wallet.currency.conversion.modals.Currency;
import com.joinzad.wallet.currency.conversion.services.ConversionServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/currency")
@RequiredArgsConstructor
public class ConversionController {

    private final ConversionServices conversionServices;

    @GetMapping("/{currency}")
    public ExchangeResponseDTO getCurrencyConversion(@PathVariable Currency currency) {
        return conversionServices.getCurrencyConversion(currency);
    }

}
