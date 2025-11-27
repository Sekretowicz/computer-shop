package com.sekretowicz.computer_shop;

import com.sekretowicz.computer_shop.dto.ProcessorDto;
import com.sekretowicz.computer_shop.model.Processor;
import com.sekretowicz.computer_shop.repo.ProcessorRepo;
import com.sekretowicz.computer_shop.service.ProcessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProcessorServiceTests {

    @Autowired
    private ProcessorService processorService;

    @Autowired
    private ProcessorRepo processorRepo;

    @BeforeEach
    void setUp() {
        processorRepo.deleteAll();

        processorRepo.save(createProcessor("Intel i3", 100, 3000, 4));
        processorRepo.save(createProcessor("Intel i5", 200, 3600, 6));
        processorRepo.save(createProcessor("Ryzen 7", 400, 4200, 8));
        processorRepo.save(createProcessor("Ryzen 9", 600, 5000, 12));
    }

    private Processor createProcessor(String title, Integer price, Integer frequency, Integer cores) {
        Processor p = new Processor();
        p.setTitle(title);
        p.setPrice(price);
        p.setFrequency(frequency);
        p.setCores(cores);
        return p;
    }

    @Test
    void shouldReturnAllProcessorsWhenNoFilters() {
        List<ProcessorDto> result = processorService.get(null, null, null, null);
        assertEquals(4, result.size());
    }

    @Test
    void shouldFilterByMinPrice() {
        List<ProcessorDto> result = processorService.get(300, null, null, null);
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(p -> p.getPrice() >= 300));
    }

    @Test
    void shouldFilterByPriceRange() {
        List<ProcessorDto> result = processorService.get(150, 450, null, null);
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(p ->
                p.getPrice() >= 150 && p.getPrice() <= 450
        ));
    }

    @Test
    void shouldFilterByFrequency() {
        List<ProcessorDto> result = processorService.get(null, null, 4000, null);
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(p -> p.getFrequency() >= 4000));
    }

    @Test
    void shouldFilterByPriceAndFrequency() {
        List<ProcessorDto> result = processorService.get(200, 500, 3500, 4500);

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(p ->
                p.getPrice() >= 200 &&
                        p.getPrice() <= 500 &&
                        p.getFrequency() >= 3500 &&
                        p.getFrequency() <= 4500
        ));
    }
}
