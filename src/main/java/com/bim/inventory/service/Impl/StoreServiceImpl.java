package com.bim.inventory.service.Impl;

import com.bim.inventory.dto.StoreDTO;
import com.bim.inventory.entity.CategoryStore;
import com.bim.inventory.entity.Store;
import com.bim.inventory.repository.CategoryStoreRepository;
import com.bim.inventory.repository.RentStoreRepository;
import com.bim.inventory.repository.SaleStoreRepository;
import com.bim.inventory.repository.StoreRepository;
import com.bim.inventory.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;



@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    SaleStoreRepository saleStoreRepository;

    @Autowired
    RentStoreRepository rentStoreRepository;

    @Autowired
    CategoryStoreRepository categoryStoreRepository;

    private static final Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class);


    @Override
    public Page<Store> getAll(Pageable pageable) throws Exception {
        return storeRepository.findAll(pageable);
    }

    @Override
    public Optional<Store> getById(Long id) throws Exception {
        if (!storeRepository.existsById(id)) {
            logger.info("Input with id " + id + " does not exists");
            return Optional.empty();
        }
        return storeRepository.findById(id);
    }


    @Override
    public Optional<Store> create(StoreDTO data) throws Exception {

        Optional<CategoryStore> optionalCategory = categoryStoreRepository.findById(data.getCategoryStoreId());

        if (!optionalCategory.isPresent()) {
            logger.info("Such ID category does not exist!");
        }

        Store store = new Store();
        store.setFullName(data.getFullName());
        store.setContractNumber(data.getContractNumber());
        store.setSize(data.getSize());
        store.setStoreNumber(data.getStoreNumber());
        store.setCategoryStore(optionalCategory.get());

        return Optional.of(storeRepository.save(store));
    }

    @Override
    public Optional<Store> update(Long id, StoreDTO data) throws Exception {

        Optional<CategoryStore> optionalCategory = categoryStoreRepository.findById(data.getCategoryStoreId());

        if (!optionalCategory.isPresent()) {
            logger.info("Such ID category does not exist!");
        }

        Optional<Store> existingStore = storeRepository.findById(id);

        if (!existingStore.isPresent()) {
            logger.info("Store with id " + id + " does not exist");
            return Optional.empty();
        }


        Store storeToUpdate = existingStore.get();

        storeToUpdate.setFullName(data.getFullName());
        storeToUpdate.setContractNumber(data.getContractNumber());
        storeToUpdate.setSize(data.getSize());
        storeToUpdate.setStoreNumber(data.getStoreNumber());
        storeToUpdate.setCategoryStore(optionalCategory.get());


        return Optional.of(storeRepository.save(storeToUpdate));
    }

    @Override
    public void deleteById(Long id) {
        if(!storeRepository.existsById(id)) {
            logger.info("Store with id " + id + " does not exists");
        }

        saleStoreRepository.deleteAll(saleStoreRepository.findAllByStoreId(id));
        rentStoreRepository.deleteAll(rentStoreRepository.findAllByStoreId(id));
        storeRepository.deleteById(id);
    }


    @Override
    public Page<Store> getAllByStoreNumberContains(int storeNumber, Pageable pageable) {
        return storeRepository.findAllByStoreNumber(storeNumber, pageable);
    }
}
