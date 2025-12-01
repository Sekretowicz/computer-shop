package com.sekretowicz.computer_shop.service;
import com.sekretowicz.computer_shop.model.GraphicsCard;
import com.sekretowicz.computer_shop.repo.GraphicsCardRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;

@Service
public class GraphicsCardService {
    @Autowired
    private GraphicsCardRepo graphicsCardRepo;
    @Autowired
    private EntityManager em;

    public List<GraphicsCard> get(String title,
                                  Integer minMemory,
                                  Integer maxMemory,
                                  Integer minPrice,
                                  Integer maxPrice) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery <GraphicsCard> cq = cb.createQuery(GraphicsCard.class);
        Root<GraphicsCard> root = cq.from(GraphicsCard.class);
        List<Predicate> predicates = new LinkedList<>();

        if (title != null) {
            predicates.add(cb.like(root.get("title"), "%" + title + "%"));
        }
        if (minPrice != null) {
            predicates.add(cb.ge(root.get("price"), minPrice));
        }
        if (maxPrice != null) {
            predicates.add(cb.le(root.get("price"), maxPrice));
        }
        if (minMemory != null) {
            predicates.add(cb.ge(root.get("memory"), minMemory));
        }
        if (maxMemory != null) {
            predicates.add(cb.le(root.get("memory"), maxMemory));
        }

        cq.select(root).where(predicates.toArray(new Predicate[0])).orderBy(cb.asc(root.get("price")));

        return em.createQuery(cq).getResultList();
    }
}