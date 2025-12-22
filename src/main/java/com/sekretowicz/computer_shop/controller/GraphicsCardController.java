package com.sekretowicz.computer_shop.controller;

import com.sekretowicz.computer_shop.dto.GraphicsCardDto;
import com.sekretowicz.computer_shop.service.GraphicsCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/graphics-cards")
public class GraphicsCardController {
    @Autowired
    private GraphicsCardService service;

    @GetMapping
    public List<GraphicsCardDto> get (@RequestParam(required = false) String title,
                                      @RequestParam(required = false) Integer maxMemory,
                                      @RequestParam(required = false) Integer maxPrice,
                                      @RequestParam(required = false) Integer minMemory,
                                      @RequestParam(required = false) Integer minPrice) {
        return service.get(title, minMemory, maxMemory, minPrice, maxPrice).stream().map(GraphicsCardDto::new).toList();
    }

    @GetMapping("/{id}")
    public GraphicsCardDto getById(@PathVariable Long id) {
        return service.getById(id);
    }
}