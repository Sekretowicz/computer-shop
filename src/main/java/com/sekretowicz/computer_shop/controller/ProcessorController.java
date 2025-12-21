package com.sekretowicz.computer_shop.controller;

import com.sekretowicz.computer_shop.dto.ErrorDto;
import com.sekretowicz.computer_shop.dto.ProcessorDto;
import com.sekretowicz.computer_shop.service.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/processors")
public class ProcessorController {
    @Autowired
    private ProcessorService service;

    @GetMapping
    public List<ProcessorDto> get(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) Integer minPrice,
                                  @RequestParam(required = false) Integer maxPrice,
                                  @RequestParam(required = false) Integer minFrequency,
                                  @RequestParam(required = false) Integer maxFrequency,
                                  @RequestParam(required = false) Integer minCores,
                                  @RequestParam(required = false) Integer maxCores) {
        return service.get(title, minPrice, maxPrice, minFrequency, maxFrequency, minCores, maxCores)
                .stream()
                .map(ProcessorDto::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ProcessorDto getById(@PathVariable Long id) {
        return service.getById(id);
    }
}