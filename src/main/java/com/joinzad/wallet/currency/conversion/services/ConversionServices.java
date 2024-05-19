package com.joinzad.wallet.currency.conversion.services;

import com.joinzad.wallet.currency.conversion.exchange.ExchangeServices;
import com.joinzad.wallet.currency.conversion.exchange.dto.ExchangeResponseDTO;
import com.joinzad.wallet.currency.conversion.modals.Currency;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConversionServices {

    @Value("${exchange.apiKey}")
    private String apiKey;

    private final ExchangeServices exchangeServices;

    private final StringRedisTemplate redisTemplate;

    @PostConstruct
    public void init() {
        log.info("Initializing ConversionServices and triggering currency conversion");
        getCurrencyConversion(Currency.TRY);
    }

    public ExchangeResponseDTO getCurrencyConversion(Currency currency) {
        ExchangeResponseDTO currencyConversion = exchangeServices.getCurrencyConversion(apiKey, currency);

        if (redisTemplate.opsForValue().get(currency.name()) == null) {
            redisTemplate.opsForValue().set(currency.name(), String.valueOf(currencyConversion.getConversionRates().get(currency.name())), 60, TimeUnit.SECONDS);
        }

        return currencyConversion;
    }


}
