package com.sekretowicz.computer_shop.service;

import com.sekretowicz.computer_shop.dto.ProcessorDto;
import com.sekretowicz.computer_shop.exception.NotFoundException;
import com.sekretowicz.computer_shop.model.Processor;
import com.sekretowicz.computer_shop.repo.ProcessorRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProcessorService {
    @Autowired
    private ProcessorRepo repo;
    @Autowired
    private EntityManager em;
    @Autowired
    private DataValidator dv;
    //Поиск процессоров по заданным параметрам - все они необязательные
    @Transactional
    public List<Processor> get(String title,
                               Integer minPrice,
                               Integer maxPrice,
                               Integer minFrequency,
                               Integer maxFrequency,
                               Integer minCores,
                               Integer maxCores) {
        //Создаем билдер, сам объект запроса (CriteriaQuery), объект таблицы (Root)
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Processor> cq = cb.createQuery(Processor.class);
        Root<Processor> root = cq.from(Processor.class);
        //Список предикатов (условий)
        List<Predicate> predicates = new LinkedList<>();

        //Добавляем только указанные параметры. Если какие-то не указаны (null) - по ним не ищем
        //ge - greater equal, le - less equal и т.д. (читай документацию)
        if (title != null) {
            predicates.add(cb.like(root.get("title"), "%" + title + "%"));
        }
        if (minPrice != null) {
            if (minPrice < 0) {
                dv.isNotNegative(minPrice, "Minimal price");
            }
            predicates.add(cb.ge(root.get("price"), minPrice));
        }
        if (maxPrice != null) {
            if (maxPrice < 0) {
                dv.isNotNegative(maxPrice, "Maximum price");
            }
            predicates.add(cb.le(root.get("price"), maxPrice));
        }
        if (minFrequency != null) {
            if (minFrequency < 0) {
                dv.isNotNegative(minFrequency, "Minimal frequency");
            }
            predicates.add(cb.ge(root.get("frequency"), minFrequency));
        }
        if (maxFrequency != null) {
            if (maxFrequency < 0) {
                dv.isNotNegative(maxFrequency, "Maximum frequency");
            }
            predicates.add(cb.le(root.get("frequency"), maxFrequency));
        }
        if (minCores != null) {
            if (minCores < 0) {
                dv.isNotNegative(minCores, "Minimal cores");
            }
            predicates.add(cb.ge(root.get("cores"), minCores));
        }
        if (maxCores != null) {
            if (maxCores < 0) {
                dv.isNotNegative(maxCores, "Maximum cores");
            }
            predicates.add(cb.le(root.get("cores"), maxCores));
        }
        //Формируем запрос
        cq.select(root).
                where(predicates.toArray(new Predicate[0]))     //Выглядит как магия, но именно так превращаем список в массив
                .orderBy(cb.asc(root.get("price")));            //В порядке возрастания цены

        //Выполняем запрос и сразу возвращаем список (одной строчкой)
        return em.createQuery(cq).getResultList();
    }

    public ProcessorDto getById(Long id) {
        Processor processor = repo.findById(id).orElseThrow(() -> new NotFoundException(String.format("Processor with id %d not found", id)));
        return new ProcessorDto(processor);
    }
}