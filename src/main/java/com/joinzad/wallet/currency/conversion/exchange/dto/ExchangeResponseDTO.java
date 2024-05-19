package com.joinzad.wallet.currency.conversion.exchange.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class ExchangeResponseDTO {

    private String result;
    private String documentation;
    @JsonProperty("terms_of_use")
    private String termsOfUse;
    @JsonProperty("time_last_update_unix")
    private long timeLastUpdateUnix;
    @JsonProperty("time_last_update_utc")
    private String timeLastUpdateUtc;

    @JsonProperty("time_next_update_unix")
    private long timeNextUpdateUnix;

    @JsonProperty("time_next_update_utc")
    private String timeNextUpdateUtc;

    @JsonProperty("base_code")
    private String baseCode;

    @JsonProperty("conversion_rates")
    private HashMap<String, Double> conversionRates;

}
