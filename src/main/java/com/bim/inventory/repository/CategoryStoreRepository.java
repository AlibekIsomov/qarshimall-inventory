package com.bim.inventory.repository;


import com.bim.inventory.entity.CategoryStore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryStoreRepository extends JpaRepository<CategoryStore, Long> {
    Page<CategoryStore> findAllByNameContains(String name, Pageable pageable);
}
