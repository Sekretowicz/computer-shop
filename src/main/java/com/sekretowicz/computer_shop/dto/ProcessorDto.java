package com.sekretowicz.computer_shop.dto;

import com.sekretowicz.computer_shop.model.Processor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessorDto {
    private String title;  // Наименование или производитель
    private int price;      //Стоимость
    private int frequency;       // Частота процессора
    private int cores;      // Число ядер

    //Конструктор Model -> DTO
    public ProcessorDto (Processor model) {
        this.price = model.getPrice();
        this.title = model.getTitle();
        this.frequency = model.getFrequency();
        this.cores = model.getCores();
        //ID в DTO нам не нужен
    }
}
