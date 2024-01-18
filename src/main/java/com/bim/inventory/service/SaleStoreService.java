package com.bim.inventory.service;

import com.bim.inventory.dto.SaleStoreDTO;
import com.bim.inventory.entity.SaleStore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface SaleStoreService {

    Optional<SaleStore> create(SaleStoreDTO data) throws Exception;

    Page<SaleStore> getAll(Pageable pageable) throws Exception;

    Optional<SaleStore> getById(Long id) throws Exception;

    Optional<SaleStore> update(Long id, SaleStoreDTO data) throws Exception;

    void deleteById(Long id);


//    Page<SaleStore> getAllByStoreNumberContains(int storeNumber, Pageable pageable);

    List<SaleStore> findItemsWithinDateRange(Instant startDate, Instant endDate);
//    List<Store> getByFullAmount(int fullAmount);



//    double getRemainingAmount(int fullAmount);


//    ResponseEntity<StoreDTO> addPayment(Long storeId, double newPayment);
//
//    double calculateTotalPaymentsByStore(Long storeId);

//    StoreDTO convertToDTO(Store store);

//    double releasePaidAmount(Long storeId, double amountToRelease);

//    double releasePaidAmount(Long storeId);

//    double releasePaidAmount(Long storeId, int fullAmount);

//    double releasePaidAmount(Long storeId, int fullAmount);
//
//    ResponseEntity<StoreDTO> updatePayment(Long storeId, Long paymentId, double newPayment);
}
