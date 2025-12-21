package com.sekretowicz.computer_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorDto {
    private int code;
    private String message;
    private LocalDateTime localDateTime;
}