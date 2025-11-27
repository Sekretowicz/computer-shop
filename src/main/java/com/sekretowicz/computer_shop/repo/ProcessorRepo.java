package com.sekretowicz.computer_shop.repo;

import com.sekretowicz.computer_shop.model.Processor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessorRepo extends JpaRepository<Processor, Long> {
    public List<Processor> findByTitle(String title);
}
