package com.bim.inventory.service;

import com.bim.inventory.dto.AdsPaymentDTO;
import com.bim.inventory.entity.AdsPayment;
import com.bim.inventory.entity.MonthlyPayment;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AdsPaymentService {
    Optional<AdsPayment> create(AdsPaymentDTO data) throws Exception;

    Optional<AdsPayment> update(Long id, AdsPaymentDTO data) throws Exception;

    //    @Override
//    public double calculateTotalPaymentsByStore(Long saleStoreId){
//        SaleStore saleStore = storeRepository.findById(saleStoreId)
//                .orElseThrow(() -> new EntityNotFoundException("Store not found with id: " + saleStoreId));
//
//        return paymentRepository.calculateTotalPaymentsByStore(saleStore);
//    }
//
//
//
//
//
    void deletePayment(Long adsPaymentId);


    ResponseEntity<List<AdsPayment>> getAllPayments(Long adsItemId);
}
