package com.sekretowicz.computer_shop.controller;

import com.sekretowicz.computer_shop.dto.PcBuildCreateDto;
import com.sekretowicz.computer_shop.service.PcBuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pc-builds")
public class PcBuildController {

    @Autowired
    private PcBuildService service;

    @PostMapping
    public void create(@RequestBody PcBuildCreateDto dto) {
        service.create(dto);
    }
}
