package com.sekretowicz.computer_shop.repo;

import com.sekretowicz.computer_shop.model.PcBuild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PcBuildRepo extends JpaRepository<PcBuild, Long> {
}
