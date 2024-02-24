package com.bim.inventory.service;

import com.bim.inventory.dto.AdsItemDTO;
import com.bim.inventory.entity.AdsItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AdsItemService {
    Page<AdsItem> getAll(Pageable pageable) throws Exception;

    Optional<AdsItem> getById(Long id) throws Exception;

    Optional<AdsItem> create(AdsItemDTO data) throws Exception;

    Optional<AdsItem> update(Long id, AdsItemDTO data) throws Exception;

    void deleteById(Long id);
}
