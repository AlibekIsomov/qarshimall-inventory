package com.bim.inventory.repository;

import com.bim.inventory.entity.AdsItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdsItemRepository extends JpaRepository<AdsItem, Long> {
}
