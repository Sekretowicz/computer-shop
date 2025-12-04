package com.sekretowicz.computer_shop;

import com.sekretowicz.computer_shop.model.Processor;
import com.sekretowicz.computer_shop.repo.ProcessorRepo;
import com.sekretowicz.computer_shop.service.ProcessorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProcessorServiceTests {

    @Autowired
    private ProcessorService processorService;

    @Autowired
    private ProcessorRepo processorRepo;

    @Test
    public void testRepo () {
        Processor created = new Processor(null, 10000, "Intel", 4, 16);
        Processor returned = processorRepo.save(created);
        assertEquals(created.getTitle(), returned.getTitle());
    }
}