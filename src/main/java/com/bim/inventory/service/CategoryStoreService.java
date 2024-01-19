package com.bim.inventory.service;

import com.bim.inventory.dto.CategoryStoreDTO;
import com.bim.inventory.entity.CategoryStore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryStoreService {
    Page<CategoryStore> getAll(Pageable pageable) throws Exception;

    Optional<CategoryStore> getById(Long id) throws Exception;

    Optional<CategoryStore> create(CategoryStoreDTO data) throws Exception;

    Optional<CategoryStore> update(Long id, CategoryStoreDTO data) throws Exception;

    Page<CategoryStore> getAllByNameContains(String name, Pageable pageable);

    void deleteById(Long id);
}
