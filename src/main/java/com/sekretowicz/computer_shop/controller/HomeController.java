package com.sekretowicz.computer_shop.controller;

import com.sekretowicz.computer_shop.dto.ProcessorDto;
import com.sekretowicz.computer_shop.model.GraphicsCard;
import com.sekretowicz.computer_shop.model.Processor;
import com.sekretowicz.computer_shop.repo.GraphicsCardRepo;
import com.sekretowicz.computer_shop.repo.ProcessorRepo;
import com.sekretowicz.computer_shop.service.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProcessorRepo processorRepo;
    @Autowired
    private GraphicsCardRepo graphicsCardRepo;
    @Autowired
    private ProcessorService processorService;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/processors")
    public String listProcessors(@RequestParam(required = false) String title,
                                 @RequestParam(required = false) Integer minPrice,
                                 @RequestParam(required = false) Integer maxPrice,
                                 @RequestParam(required = false) Integer minFrequency,
                                 @RequestParam(required = false) Integer maxFrequency,
            Model model) {
        List<Processor> processors = processorService.get(title, minPrice, maxPrice, minFrequency, maxFrequency); // или твой метод
        model.addAttribute("processors", processors);
        return "processors"; // -> processors.html
    }

    @GetMapping("/processors/{id}")
    public String processorDetails(@PathVariable Long id, Model model) {
        Processor processor = processorRepo.findById(id).get(); // или как у тебя называется

        model.addAttribute("processor", processor);
        model.addAttribute("activePage", "processors");

        return "processor-details";
    }

    @GetMapping("/graphics-cards")
    public String listGraphicsCards(Model model) {
        List<GraphicsCard> graphicsCards = graphicsCardRepo.findAll(); // или твой метод
        model.addAttribute("graphics-cards", graphicsCards);
        return "graphics-cards";
    }
}