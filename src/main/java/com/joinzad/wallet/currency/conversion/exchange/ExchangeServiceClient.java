package com.joinzad.wallet.currency.conversion.exchange;

import com.joinzad.wallet.account.models.Currency;
import com.joinzad.wallet.currency.conversion.configurations.ExchangeClientFeignConfig;
import com.joinzad.wallet.currency.conversion.exchange.dto.ExchangeResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        value = "exchangeClient",
        url = "${exchange.url}",
        configuration = ExchangeClientFeignConfig.class)
public interface ExchangeServiceClient {
    @GetMapping("/v6/{apiKey}/latest/{currency}")
    ExchangeResponseDTO getCurrencyConversion(@PathVariable String apiKey, @PathVariable Currency currency);
}
