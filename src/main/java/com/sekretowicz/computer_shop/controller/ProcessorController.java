package com.sekretowicz.computer_shop.controller;

import com.sekretowicz.computer_shop.dto.ProcessorDto;
import com.sekretowicz.computer_shop.service.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/controllers")
public class ProcessorController {
    @Autowired
    private ProcessorService service;

    @GetMapping
    public List<ProcessorDto> get(@RequestParam(required = false) Integer minPrice,
                                  @RequestParam(required = false) Integer maxPrice,
                                  @RequestParam(required = false) Integer minFrequency,
                                  @RequestParam(required = false) Integer maxFrequency) {
        return service.get(minPrice, maxPrice, minFrequency, maxFrequency)
                .stream()
                .map(ProcessorDto::new)
                .toList();
    }
}
