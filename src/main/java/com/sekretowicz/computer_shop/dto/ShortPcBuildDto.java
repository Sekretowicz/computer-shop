package com.sekretowicz.computer_shop.dto;

import com.sekretowicz.computer_shop.model.PcBuild;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortPcBuildDto {

    private String buildTitle;
    private String processorTitle;
    private String graphicsCardTitle;
    private BigDecimal totalPrice;

    public ShortPcBuildDto(PcBuild model) {
        this.buildTitle = model.getTitle();
        this.processorTitle = model.getProcessor().getTitle();
        this.graphicsCardTitle = model.getGraphicsCard().getTitle();
        this.totalPrice = new BigDecimal(model.getProcessor().getPrice() + model.getGraphicsCard().getPrice());
    }
}