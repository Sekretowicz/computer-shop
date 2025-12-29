package com.sekretowicz.computer_shop.service;

import com.sekretowicz.computer_shop.client.ExchangerRatesClient;
import com.sekretowicz.computer_shop.dto.PcBuildCreateDto;
import com.sekretowicz.computer_shop.dto.ShortPcBuildDto;
import com.sekretowicz.computer_shop.exception.NotFoundException;
import com.sekretowicz.computer_shop.model.PcBuild;
import com.sekretowicz.computer_shop.repo.PcBuildRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class PcBuildService {
    @Autowired
    private EntityManager em;
    @Autowired
    private PcBuildRepo repo;
    @Autowired
    private ExchangerRatesClient erc;

    @Transactional
    public void create(PcBuildCreateDto dto) {
        //Сама строка запроса. Параметры с двоеточиями подставляются через setParameter.
        String sqlString = "INSERT INTO pc_builds (title, processor_id, graphics_card_id) " +
                "VALUES (:title, :processor_id, :graphics_card_id)";

        //Создаем объект Query - сам запрос, и заполняем параметры из DTO.
        Query query = em.createNativeQuery(sqlString)
                .setParameter("title", dto.getTitle())
                .setParameter("processor_id", dto.getProcessorId())
                .setParameter("graphics_card_id", dto.getGraphicsCardId());

        query.executeUpdate();
    }

    public ShortPcBuildDto calculatePrice(Long id, String currency) {
        PcBuild pcBuild = repo.findById(id).orElseThrow(() -> new NotFoundException(String.format("PcBuild with id %d not found", id)));
        ShortPcBuildDto dto = new ShortPcBuildDto(pcBuild);
        BigDecimal totalPrice = dto.getTotalPrice().multiply(erc.getRate(currency));
        dto.setTotalPrice(totalPrice);
        return dto;
    }
}