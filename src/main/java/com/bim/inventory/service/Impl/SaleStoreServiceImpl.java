package com.bim.inventory.service.Impl;


import com.bim.inventory.dto.SaleStoreDTO;
import com.bim.inventory.entity.Payment;
import com.bim.inventory.entity.SaleStore;
import com.bim.inventory.entity.Store;
import com.bim.inventory.repository.PaymentRepository;
import com.bim.inventory.repository.SaleStoreRepository;
import com.bim.inventory.repository.StoreRepository;
import com.bim.inventory.service.SaleStoreService;
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
public class SaleStoreServiceImpl implements SaleStoreService {

    private static final Logger logger = LoggerFactory.getLogger(SaleStoreServiceImpl.class);

    @Autowired
    SaleStoreRepository saleStoreRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    StoreRepository storeRepository;



    @Override
    public Page<SaleStore> getAll(Pageable pageable) throws Exception {
        return saleStoreRepository.findAll(pageable);
    }

    @Override
    public Optional<SaleStore> getById(Long id) throws Exception {
        if (!storeRepository.existsById(id)) {
            logger.info("Input with id " + id + " does not exists");
            return Optional.empty();
        }
        return saleStoreRepository.findById(id);
    }


    @Override
    public Optional<SaleStore> create(SaleStoreDTO data) throws Exception {

        Optional<Store> optionalStore = storeRepository.findById(data.getStoreId());

        if (!optionalStore.isPresent()) {
            logger.info("Such ID category does not exist!");
        }


        SaleStore saleStore = new SaleStore();
        saleStore.setFullAmount(data.getFullAmount());
        saleStore.setInitialPayment(data.getInitialPayment());
        saleStore.setStore(optionalStore.get());

        return Optional.of(saleStoreRepository.save(saleStore));
    }

    @Override
    public Optional<SaleStore> update(Long id, SaleStoreDTO data) throws Exception {
        Optional<SaleStore> existingStore = saleStoreRepository.findById(id);

        Optional<Store> optionalStore = storeRepository.findById(data.getStoreId());


        if (!optionalStore.isPresent()) {
            logger.info("Such ID category does not exist!");
        }

        if (!existingStore.isPresent()) {
            logger.info("Store with id " + id + " does not exist");
            return Optional.empty();
        }


        SaleStore saleStoreToUpdate = existingStore.get();

        saleStoreToUpdate.setFullAmount(data.getFullAmount());
        saleStoreToUpdate.setInitialPayment(data.getInitialPayment());
        saleStoreToUpdate.setStore(optionalStore.get());


        return Optional.of(saleStoreRepository.save(saleStoreToUpdate));
    }

    @Override
    public void deleteById(Long id) {
        if(!saleStoreRepository.existsById(id)) {
            logger.info("Store with id " + id + " does not exists");
        }

        saleStoreRepository.deleteById(id);
    }


//    @Override
//    public Page<SaleStore> getAllByStoreNumberContains(int storeNumber, Pageable pageable) {
//        return storeRepository.findAllByStoreNumber(storeNumber, pageable);
//    }


    @Override
    public List<SaleStore> findItemsWithinDateRange(Instant startDate, Instant endDate) {
        return saleStoreRepository.findByCreatedAtBetween(startDate, endDate);

    }



}
