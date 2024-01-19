package com.bim.inventory.service.Impl;


import com.bim.inventory.dto.CategoryStoreDTO;
import com.bim.inventory.entity.CategoryStore;
import com.bim.inventory.repository.*;
import com.bim.inventory.service.CategoryStoreService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryStoreServiceImpl implements CategoryStoreService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryStoreServiceImpl.class);
    @Autowired
    CategoryStoreRepository categoryStoreRepository;

    @Autowired
    StoreRepository storeRepository;


    @Override
    public Page<CategoryStore> getAll(Pageable pageable) throws Exception {
        return categoryStoreRepository.findAll(pageable);
    }

    @Override
    public Optional<CategoryStore> getById(Long id) throws Exception {
        if(!categoryStoreRepository.existsById(id)) {
            logger.info("Input with id " + id + " does not exists");
            return Optional.empty();
        }
        return categoryStoreRepository.findById(id);
    }


    @Override
    public Optional<CategoryStore> create(CategoryStoreDTO data) throws Exception {

        CategoryStore categoryStore = new CategoryStore();
        categoryStore.setName(data.getName());

        return Optional.of(categoryStoreRepository.save(categoryStore));
    }
    @Override
    public Optional<CategoryStore> update(Long id, CategoryStoreDTO data) throws Exception {
        Optional<CategoryStore> optionalCategory = categoryStoreRepository.findById(id);

        if (optionalCategory.isPresent()) {
            CategoryStore categoryStore = optionalCategory.get();
            categoryStore.setName(data.getName());

            // Save the updated category
            return Optional.of(categoryStoreRepository.save(categoryStore));
        } else {
            // Handle the case where the category with the given ID doesn't exist
            throw new NotFoundException("Category not found with ID: " + id);
        }
    }

    @Override
    public Page<CategoryStore> getAllByNameContains(String name, Pageable pageable) {
        return categoryStoreRepository.findAllByNameContains(name,pageable);
    }

    @Override
    public void deleteById(Long id) {
        if(!categoryStoreRepository.existsById(id)) {
            logger.info("Input with id " + id + " does not exists");
        }
        storeRepository.deleteAll(storeRepository.findAllByCategoryStoreId(id));

        categoryStoreRepository.deleteById(id);
    }
}
