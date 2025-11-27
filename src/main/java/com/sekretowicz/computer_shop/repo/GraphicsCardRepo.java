package com.sekretowicz.computer_shop.repo;

import com.sekretowicz.computer_shop.model.GraphicsCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphicsCardRepo extends JpaRepository<GraphicsCard, Long> {
}
