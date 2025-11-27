package com.sekretowicz.computer_shop.model;

import com.sekretowicz.computer_shop.dto.ProcessorDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "processors")
public class Processor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        // ID в базе данных

    private int price;      //Стоимость
    private String title;   // Наименование или производитель
    private int frequency;  // Частота процессора
    private int cores;      // Число ядер

    //Конструктор DTO -> Model
    public Processor (ProcessorDto dto) {
        this.price = dto.getPrice();
        this.title = dto.getTitle();
        this.frequency = dto.getFrequency();
        this.cores = dto.getCores();
        //Примечание: ID будет назначаться автоматически при помещении в БД
    }
}