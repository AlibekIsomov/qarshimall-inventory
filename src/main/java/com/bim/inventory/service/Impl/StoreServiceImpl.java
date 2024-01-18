package com.bim.inventory.service.Impl;

import com.bim.inventory.dto.SaleStoreDTO;
import com.bim.inventory.dto.StoreDTO;
import com.bim.inventory.entity.SaleStore;
import com.bim.inventory.entity.Store;
import com.bim.inventory.repository.PaymentRepository;
import com.bim.inventory.repository.SaleStoreRepository;
import com.bim.inventory.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;



@Service
public class StoreServiceImpl {

    @Autowired
    StoreRepository storeRepository;




//    @Override
//    public Page<Store> getAll(Pageable pageable) throws Exception {
//        return storeRepository.findAll(pageable);
//    }
//
//    @Override
//    public Optional<Store> getById(Long id) throws Exception {
//        if (!storeRepository.existsById(id)) {
//            logger.info("Input with id " + id + " does not exists");
//            return Optional.empty();
//        }
//        return storeRepository.findById(id);
//    }
//
//
//    @Override
//    public Optional<Store> create(StoreDTO data) throws Exception {
//
//        SaleStore saleStore = new SaleStore();
//        saleStore.setFullAmount(data.getFullAmount());
//        saleStore.setInitialPayment(data.getInitialPayment());
//
//        return Optional.of(storeRepository.save(saleStore));
//    }
//
//    @Override
//    public Optional<SaleStore> update(Long id, SaleStoreDTO data) throws Exception {
//        Optional<SaleStore> existingStore = storeRepository.findById(id);
//
//        if (!existingStore.isPresent()) {
//            logger.info("Store with id " + id + " does not exist");
//            return Optional.empty();
//        }
//
//
//        SaleStore saleStoreToUpdate = existingStore.get();
//
//        saleStoreToUpdate.setFullAmount(data.getFullAmount());
//        saleStoreToUpdate.setInitialPayment(data.getInitialPayment());
//
//
//        return Optional.of(storeRepository.save(saleStoreToUpdate));
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        if(!storeRepository.existsById(id)) {
//            logger.info("Store with id " + id + " does not exists");
//        }
//        storeRepository.deleteById(id);
//    }
//
//
//    @Override
//    public Page<SaleStore> getAllByStoreNumberContains(int storeNumber, Pageable pageable) {
//        return storeRepository.findAllByStoreNumber(storeNumber, pageable);
//    }
//
//
//    @Override
//    public List<SaleStore> findItemsWithinDateRange(Instant startDate, Instant endDate) {
//        return storeRepository.findByCreatedAtBetween(startDate, endDate);
//
//    }
}
