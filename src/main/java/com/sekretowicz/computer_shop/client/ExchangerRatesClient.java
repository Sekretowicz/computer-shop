package com.sekretowicz.computer_shop.client;

import com.sekretowicz.computer_shop.dto.ExchangeRatesResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.time.Instant;

@Service
public class ExchangerRatesClient {
    private String BASE_URL = "https://v6.exchangerate-api.com/v6";
    private String API_KEY = "87e9f21ac826800c0a8f88b0";
    private ExchangeRatesResponse dto = null;

    private void refresh() {
        RestTemplate rt = new RestTemplate();
        String url = String.format("%s/%s/latest/USD", BASE_URL, API_KEY);
        dto = rt.getForEntity(url, ExchangeRatesResponse.class).getBody();
    }

    public BigDecimal getRate(String currency) {
        if (dto == null || Instant.now().getEpochSecond() >= dto.getTimeNextUpdateRates()) {
            this.refresh();
        }
        return dto.getRates().get(currency);
    }

    public ExchangeRatesResponse seeRates() {
        if (dto == null || Instant.now().getEpochSecond() >= dto.getTimeNextUpdateRates()) {
            this.refresh();
        }
        return dto;
    }
}