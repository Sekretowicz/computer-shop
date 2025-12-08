package com.sekretowicz.computer_shop;

import com.sekretowicz.computer_shop.model.Processor;
import com.sekretowicz.computer_shop.repo.ProcessorRepo;
import com.sekretowicz.computer_shop.service.ProcessorService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProcessorServiceTests {

    @Autowired
    private ProcessorService processorService;

    @Autowired
    private ProcessorRepo processorRepo;

    public void prepare() {
        List<Processor> processors = new LinkedList<>();

        processors.add(new Processor(null, 45000, "Intel Core i9-14900K", 5500, 24));
        processors.add(new Processor(null, 28000, "AMD Ryzen 9 7950X", 5500, 16));
        processors.add(new Processor(null, 18000, "Intel Core i7-14700K", 5400, 20));
        processors.add(new Processor(null, 15000, "AMD Ryzen 7 7800X3D", 5000, 8));
        processors.add(new Processor(null, 12000, "Intel Core i5-14600K", 5100, 14));
        processors.add(new Processor(null, 9000, "AMD Ryzen 5 7600X", 5300, 6));
        processors.add(new Processor(null, 6000, "Intel Core i3-14100", 4400, 4));
        processors.add(new Processor(null, 32000, "AMD Threadripper 7960X", 4500, 24));
        processors.add(new Processor(null, 8000, "Intel Core i5-13400", 4600, 10));
        processors.add(new Processor(null, 11000, "AMD Ryzen 7 7700X", 5400, 8));

        processorRepo.saveAll(processors);
    }

    @Test
    public void testRepoByTitle () {
        Processor created = new Processor(null, 10000, "Intel", 4, 16);
        Processor returned = processorRepo.save(created);
        assertEquals(created.getTitle(), returned.getTitle());
    }

    @Test
    public void testCriteriaApiByTitle () {
        this.prepare();
        List<Processor> created = processorService.get("Intel", null, null,null,null);
        assertEquals(5,created.size());
    }

    @Test
    public void testCriteriaApiByMinAndMaxFrequency () {
        this.prepare();
        List<Processor> created = processorService.get(null, null, null,5100,5300);
        assertEquals(2,created.size());
    }
}