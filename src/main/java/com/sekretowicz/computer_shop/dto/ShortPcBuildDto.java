package com.sekretowicz.computer_shop.dto;

import com.sekretowicz.computer_shop.model.PcBuild;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortPcBuildDto {

    private String buildTitle;
    private String processorTitle;
    private String graphicsCardTitle;
    private int totalPrice;

    public ShortPcBuildDto(PcBuild model) {
        this.buildTitle = model.getName();
        this.processorTitle = model.getProcessor().getTitle();
        this.graphicsCardTitle = model.getGraphicsCard().getTitle();
        this.totalPrice = model.getProcessor().getPrice() + model.getGraphicsCard().getPrice();
    }
}
