package com.sekretowicz.computer_shop.service;

import com.sekretowicz.computer_shop.exception.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public class DataValidator {
    public void isNotNegative (Integer i, String name) {
        if (i != null && i < 0) {
            throw new BadRequestException(String.format("%s must be greater then 0", name));
        }
    }
}

//ДЗ: РЕАЛИЗОВАТЬ