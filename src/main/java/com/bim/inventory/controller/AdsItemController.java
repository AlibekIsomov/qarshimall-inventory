package com.bim.inventory.controller;


import com.bim.inventory.dto.AdsItemDTO;
import com.bim.inventory.dto.CategoryDTO;
import com.bim.inventory.entity.AdsItem;
import com.bim.inventory.entity.Category;
import com.bim.inventory.repository.AdsItemRepository;
import com.bim.inventory.service.AdsItemService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/adsItem")
public class AdsItemController {

    @Autowired
    AdsItemService adsItemService;

    @GetMapping
    public ResponseEntity<Page<AdsItem>> getAll(Pageable pageable) throws Exception {
        return ResponseEntity.ok(adsItemService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdsItem> getById(@PathVariable Long id) throws Exception {
        return adsItemService.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AdsItem> create(@RequestBody AdsItemDTO data) throws Exception {
        try {
            Optional<AdsItem> createdCategory = adsItemService.create(data);

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
    public ResponseEntity<AdsItem> update(@PathVariable Long id,
                                           @RequestBody AdsItemDTO data) throws Exception {
        try {
            Optional<AdsItem> createdCategory = adsItemService.update(id, data);

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
        adsItemService.deleteById(id);
    }

}
