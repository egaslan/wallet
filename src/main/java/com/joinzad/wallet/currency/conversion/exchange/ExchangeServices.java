package com.joinzad.wallet.currency.conversion.exchange;

import com.joinzad.wallet.account.models.Currency;
import com.joinzad.wallet.currency.conversion.exchange.dto.ExchangeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@EnableFeignClients(basePackages = {"com.joinzad.wallet.currency.conversion.exchange"})
public class ExchangeServices {

    private final ExchangeServiceClient exchangeServiceClient;
    public ExchangeResponseDTO getCurrencyConversion(String apiKey, Currency currency) {

        try {
            return exchangeServiceClient.getCurrencyConversion(apiKey,currency);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
