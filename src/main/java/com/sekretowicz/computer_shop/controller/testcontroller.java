package com.sekretowicz.computer_shop.controller;

import com.sekretowicz.computer_shop.client.ExchangerRatesClient;
import com.sekretowicz.computer_shop.dto.ExchangeRatesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testcontroller {
    @Autowired
    ExchangerRatesClient erc;
    @GetMapping("/api/test")
    public ExchangeRatesResponse aaa() {
        return erc.getRates();
    }
}