package com.sekretowicz.computer_shop;

import com.sekretowicz.computer_shop.model.GraphicsCard;
import com.sekretowicz.computer_shop.repo.GraphicsCardRepo;
import com.sekretowicz.computer_shop.service.GraphicsCardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class GraphicsCardServiceTests {
    @Autowired
    private GraphicsCardService graphicsCardService;

    @Autowired
    private GraphicsCardRepo graphicsCardRepo;

    @Test
    public void testRepo() {
        GraphicsCard created = new GraphicsCard(null, "NVIDIA", 16, 12000);
        GraphicsCard returned = graphicsCardRepo.save(created);
        assertEquals(created.getTitle(), returned.getTitle());
    }
}