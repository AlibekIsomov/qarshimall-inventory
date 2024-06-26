package com.bim.inventory.repository;


import com.bim.inventory.entity.InputItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface InputItemRepository extends JpaRepository<InputItem, Long> {

    Page<InputItem> findAllByNameContains(String name, Pageable pageable);

    List<InputItem> findByCreatedAtBetween(Instant startDate, Instant endDate);

    List<InputItem> findAllByCategoryId(Long id);

}
