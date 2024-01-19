package com.bim.inventory.controller;


import com.bim.inventory.dto.CategoryDTO;
import com.bim.inventory.dto.CategoryStoreDTO;
import com.bim.inventory.entity.Category;
import com.bim.inventory.entity.CategoryStore;
import com.bim.inventory.repository.CategoryRepository;
import com.bim.inventory.repository.CategoryStoreRepository;
import com.bim.inventory.service.CategoryService;
import com.bim.inventory.service.CategoryStoreService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/categoryStore")
public class CategoryStoreController {

    @Autowired
    CategoryStoreRepository categoryStoreRepository;
    @Autowired
    CategoryStoreService categoryStoreService;

    @GetMapping
    public ResponseEntity<Page<CategoryStore>> getAll(Pageable pageable) throws Exception {
        return ResponseEntity.ok(categoryStoreService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryStore> getById(@PathVariable Long id) throws Exception {
        return categoryStoreService.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoryStore> create(@RequestBody CategoryStoreDTO data) throws Exception {
        try {
            Optional<CategoryStore> createdCategory = categoryStoreService.create(data);

            if(createdCategory.isPresent()){
                return ResponseEntity.ok(createdCategory.get());
            }
            else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryStore> update(@PathVariable Long id,
                                           @RequestBody CategoryStoreDTO data) throws Exception {
        try {
            Optional<CategoryStore> createdCategory = categoryStoreService.update(id, data);

            if (createdCategory.isPresent()) {
                return ResponseEntity.ok(createdCategory.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NotFoundException categoryNotFoundException) {

            return ResponseEntity.badRequest().build();
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        categoryStoreService.deleteById(id);
    }

    @GetMapping("/search-name/{name}")
    public ResponseEntity<Page<CategoryStore>> searchName(@PathVariable String name, Pageable pageable) {
        return ResponseEntity.ok(categoryStoreService.getAllByNameContains(name,pageable));
    }
}
