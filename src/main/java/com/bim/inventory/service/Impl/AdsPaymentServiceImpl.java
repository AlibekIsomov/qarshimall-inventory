package com.bim.inventory.service.Impl;


import com.bim.inventory.dto.AdsPaymentDTO;
import com.bim.inventory.entity.*;
import com.bim.inventory.repository.AdsItemRepository;
import com.bim.inventory.repository.AdsPaymentRepository;
import com.bim.inventory.service.AdsPaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdsPaymentServiceImpl implements AdsPaymentService {

    private static final Logger logger = LoggerFactory.getLogger(MonthlyPaymentServiceImpl.class);

    @Autowired
    AdsPaymentRepository adsPaymentRepository;

    @Autowired
    AdsItemRepository adsItemRepository;


    @Override
    public Optional<AdsPayment> create(AdsPaymentDTO data) throws Exception {

        Optional<AdsItem> adsItemOptional = adsItemRepository.findById(data.getAdsItemId());

        if (!adsItemOptional.isPresent()) {
            logger.info("Such ID category does not exist!");
        }
        PaymentStatus paymentStatus = PaymentStatus.valueOf(data.getStatus());

        AdsPayment adsPayment = new AdsPayment();

        adsPayment.setClientName(data.getClientName());
        adsPayment.setContractNumber(data.getContractNumber());
        adsPayment.setPaymentAmount(data.getPaymentAmount());
        adsPayment.setToDate(data.getToDate());
        adsPayment.setFromDate(data.getFromDate());
        adsPayment.setAdsItem(adsItemOptional.get());
        adsPayment.setPaidAmount(data.getPaidAmount());
        adsPayment.setStatus(paymentStatus);

        return Optional.of(adsPaymentRepository.save(adsPayment));
    }


    @Override
    public Optional<AdsPayment> update(Long id, AdsPaymentDTO data) throws Exception {

        Optional<AdsPayment> adsPaymentOptional = adsPaymentRepository.findById(data.getAdsItemId());

        if (!adsPaymentOptional.isPresent()) {
            logger.info("Such ID category does not exist!");
        }
        PaymentStatus paymentStatus = PaymentStatus.valueOf(data.getStatus());

        AdsPayment adsPayment = adsPaymentOptional.get();

        adsPayment.setClientName(data.getClientName());
        adsPayment.setContractNumber(data.getContractNumber());
        adsPayment.setPaymentAmount(data.getPaymentAmount());
        adsPayment.setStatus(paymentStatus);
        adsPayment.setPaidAmount(data.getPaidAmount());
        adsPayment.setToDate(data.getToDate());
        adsPayment.setFromDate(data.getFromDate());



        return Optional.of(adsPaymentRepository.save(adsPayment));
    }

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
    @Override
    public void deletePayment(Long adsPaymentId) {
        Optional<AdsPayment> adsPayment = adsPaymentRepository.findById(adsPaymentId);

        if (adsPayment.isPresent()) {
            AdsPayment paymentToDelete = adsPayment.get();

            // Remove the payment from the associated store's payments list
            AdsItem adsItem = paymentToDelete.getAdsItem();
            if (adsItem != null) {
                adsItem.getAdsPayments().remove(paymentToDelete);
            }

            // Delete the payment from the database
            adsPaymentRepository.delete(paymentToDelete);
        } else {
            // Handle the case where the payment with the given id is not found
            // You can throw an exception, log a message, or handle it in another way.
            // For simplicity, I'll log a message.
            System.out.println("Payment with id " + adsPaymentId + " not found");
        }
    }

    @Override
    public ResponseEntity<List<AdsPayment>> getAllPayments(Long adsItemId) {
        List<AdsPayment> adsPayments = adsPaymentRepository.findByAdsItemId(adsItemId);

        return ResponseEntity.ok(adsPayments);
    }



}
