package com.bim.inventory.service;

import com.bim.inventory.dto.StoreDTO;
import com.bim.inventory.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StoreService {
    Page<Store> getAll(Pageable pageable) throws Exception;

    Optional<Store> getById(Long id) throws Exception;

    Optional<Store> create(StoreDTO data) throws Exception;

    Optional<Store> update(Long id, StoreDTO data) throws Exception;

    void deleteById(Long id);

    Page<Store> getAllByStoreNumberContains(int storeNumber, Pageable pageable);
}
