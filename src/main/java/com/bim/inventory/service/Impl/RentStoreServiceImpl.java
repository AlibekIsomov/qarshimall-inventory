package com.bim.inventory.service.Impl;


import com.bim.inventory.dto.RentStoreDTO;
import com.bim.inventory.entity.RentStore;
import com.bim.inventory.entity.Store;
import com.bim.inventory.repository.RentStoreRepository;
import com.bim.inventory.repository.StoreRepository;
import com.bim.inventory.service.RentStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class RentStoreServiceImpl implements RentStoreService {

    private static final Logger logger = LoggerFactory.getLogger(RentStoreServiceImpl.class);

    @Autowired
    RentStoreRepository rentStoreRepository;

    @Autowired
    StoreRepository storeRepository;



    @Override
    public Page<RentStore> getAll(Pageable pageable) throws Exception {
        return rentStoreRepository.findAll(pageable);
    }

    @Override
    public Optional<RentStore> getById(Long id) throws Exception {
        if (!rentStoreRepository.existsById(id)) {
            logger.info("Input with id " + id + " does not exists");
            return Optional.empty();
        }
        return rentStoreRepository.findById(id);
    }


    @Override
    public Optional<RentStore> create(RentStoreDTO data) throws Exception {

        Optional<Store> optionalStore = storeRepository.findById(data.getStoreId());

        if (!optionalStore.isPresent()) {
            logger.info("Such ID category does not exist!");
        }

        RentStore rentStore = new RentStore();
        rentStore.setRentingAmount(data.getRentingAmount());
        rentStore.setStore(optionalStore.get());

        return Optional.of(rentStoreRepository.save(rentStore));
    }

    @Override
    public Optional<RentStore> update(Long id, RentStoreDTO data) throws Exception {

        Optional<Store> optionalStore = storeRepository.findById(data.getStoreId());


        Optional<RentStore> existingStore = rentStoreRepository.findById(id);

        if (!existingStore.isPresent()) {
            logger.info("Store with id " + id + " does not exist");
            return Optional.empty();
        }

        if (!optionalStore.isPresent()) {
            logger.info("Such ID category does not exist!");
        }

        RentStore rentStoreToUpdate = existingStore.get();

        rentStoreToUpdate.setRentingAmount(data.getRentingAmount());
        rentStoreToUpdate.setStore(optionalStore.get());


        return Optional.of(rentStoreRepository.save(rentStoreToUpdate));
    }

    @Override
    public void deleteById(Long id) {
        if(!rentStoreRepository.existsById(id)) {
            logger.info("Store with id " + id + " does not exists");
        }
        rentStoreRepository.deleteById(id);
    }

    @Override
    public List<RentStore> findItemsWithinDateRange(Instant startDate, Instant endDate) {
        return rentStoreRepository.findByCreatedAtBetween(startDate, endDate);

    }
}
