package com.sekretowicz.computer_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PcBuildCreateDto {
    private String title;
    private Long processorId;
    private Long graphicsCardId;
}
