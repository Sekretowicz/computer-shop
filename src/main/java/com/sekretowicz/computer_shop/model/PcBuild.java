package com.sekretowicz.computer_shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pc_builds")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PcBuild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private Processor processor;

    @ManyToOne(fetch = FetchType.LAZY)
    private GraphicsCard graphicsCard;

}