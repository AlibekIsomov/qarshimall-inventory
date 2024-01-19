package com.bim.inventory.repository;

import com.bim.inventory.entity.SaleStore;
import com.bim.inventory.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Page<Store> findAllByStoreNumber(int storeNumber, Pageable pageable);

    Iterable<? extends Store> findAllByCategoryStoreId(Long id);
}
