package com.sekretowicz.computer_shop.dto;

import com.sekretowicz.computer_shop.model.GraphicsCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphicsCardDto {
    private String title;
    private int memory;
    private int price;

    public GraphicsCardDto (GraphicsCard model) {
        this.title = model.getTitle();
        this.memory = model.getMemory();
        this.price = model.getPrice();
    }
}
