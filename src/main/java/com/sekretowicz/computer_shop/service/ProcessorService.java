package com.sekretowicz.computer_shop.service;

import com.sekretowicz.computer_shop.dto.ProcessorDto;
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

    //Поиск процессоров по заданным параметрам - все они необязательные
    @Transactional
    public List<Processor> get(Integer minPrice,
                                  Integer maxPrice,
                                  Integer minFrequency,
                                  Integer maxFrequency) {
        //Создаем билдер, сам объект запроса (CriteriaQuery), объект таблицы (Root)
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Processor> cq = cb.createQuery(Processor.class);
        Root<Processor> root = cq.from(Processor.class);
        //Список предикатов (условий)
        List<Predicate> predicates = new LinkedList<>();

        //Добавляем только указанные параметры. Если какие-то не указаны (null) - по ним не ищем
        //ge - greater equal, le - less equal и т.д. (читай документацию)
        if (minPrice != null) {
            predicates.add(cb.ge(root.get("price"), minPrice));
        }   
        if (maxPrice != null) {
            predicates.add(cb.le(root.get("price"), maxPrice));
        }
        if (minFrequency != null) {
            predicates.add(cb.ge(root.get("frequency"), minFrequency));
        }
        if (maxFrequency != null) {
            predicates.add(cb.le(root.get("frequency"), maxFrequency));
        }

        //Формируем запрос
        cq.select(root).
                where(predicates.toArray(new Predicate[0]))     //Выглядит как магия, но именно так превращаем список в массив
                .orderBy(cb.asc(root.get("price")));            //В порядке возрастания цены

        //Выполняем запрос и сразу возвращаем список (одной строчкой)
        return em.createQuery(cq).getResultList();
    }
}
