package com.sekretowicz.computer_shop.controller;

import com.sekretowicz.computer_shop.dto.PcBuildCreateDto;
import com.sekretowicz.computer_shop.service.PcBuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/pc-builds")
public class PcBuildController {

    @Autowired
    private PcBuildService service;

    @PostMapping
    public void create(@RequestBody PcBuildCreateDto dto) {
        service.create(dto);
    }

    public void calculatePrice(@PathVariable Long id, @RequestParam String currency) {
        service.calculatePrice(id, currency);
    }
}