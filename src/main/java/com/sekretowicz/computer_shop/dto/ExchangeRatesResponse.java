package com.sekretowicz.computer_shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Map;

@Data
public class ExchangeRatesResponse {
    @JsonProperty("time_next_update_unix")
    private Long timeNextUpdateRates;
    @JsonProperty("conversion_rates")
    private Map<String, BigDecimal> rates;
}