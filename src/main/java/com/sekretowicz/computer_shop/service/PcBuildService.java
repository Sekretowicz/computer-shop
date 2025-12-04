package com.sekretowicz.computer_shop.service;

import com.sekretowicz.computer_shop.dto.PcBuildCreateDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PcBuildService {

    @Autowired
    private EntityManager em;

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
}
