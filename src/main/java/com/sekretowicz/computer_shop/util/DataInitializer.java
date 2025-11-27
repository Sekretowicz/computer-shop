package com.sekretowicz.computer_shop.util;

import com.sekretowicz.computer_shop.model.GraphicsCard;
import com.sekretowicz.computer_shop.model.PcBuild;
import com.sekretowicz.computer_shop.model.Processor;
import com.sekretowicz.computer_shop.repo.GraphicsCardRepo;
import com.sekretowicz.computer_shop.repo.PcBuildRepo;
import com.sekretowicz.computer_shop.repo.ProcessorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProcessorRepo processorRepository;
    @Autowired
    private GraphicsCardRepo graphicsCardRepository;
    @Autowired
    private PcBuildRepo pcBuildRepository;

    @Override
    @Transactional
    public void run(String... args) {
        // Do not duplicate data on every restart
        if (processorRepository.count() > 0 || graphicsCardRepository.count() > 0 || pcBuildRepository.count() > 0) {
            return;
        }

        // Processors
        Processor i5 = new Processor();
        i5.setTitle("Intel Core i5-12400F");
        i5.setCores(6);
        i5.setFrequency(2500);
        i5.setPrice(18000);
        processorRepository.save(i5);

        Processor ryzen5 = new Processor();
        ryzen5.setTitle("AMD Ryzen 5 5600X");
        ryzen5.setCores(6);
        ryzen5.setFrequency(3700);
        ryzen5.setPrice(20000);
        processorRepository.save(ryzen5);

        // GPUs
        GraphicsCard rtx3060 = new GraphicsCard();
        rtx3060.setTitle("NVIDIA GeForce RTX 3060");
        rtx3060.setMemory(12);
        rtx3060.setPrice(35000);
        graphicsCardRepository.save(rtx3060);

        GraphicsCard rx6600 = new GraphicsCard();
        rx6600.setTitle("AMD Radeon RX 6600");
        rx6600.setMemory(8);
        rx6600.setPrice(28000);
        graphicsCardRepository.save(rx6600);

        // Builds
        PcBuild gamingPc = new PcBuild();
        gamingPc.setName("Gaming PC #1");
        gamingPc.setProcessor(ryzen5);
        gamingPc.setGraphicsCard(rtx3060);
        pcBuildRepository.save(gamingPc);

        PcBuild budgetPc = new PcBuild();
        budgetPc.setName("Budget PC");
        budgetPc.setProcessor(i5);
        budgetPc.setGraphicsCard(rx6600);
        pcBuildRepository.save(budgetPc);


        List<Object> foo = new ArrayList<Object>();

    }
}