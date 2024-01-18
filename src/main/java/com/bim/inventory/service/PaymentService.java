package com.bim.inventory.service;

import com.bim.inventory.dto.PaymentDTO;
import com.bim.inventory.dto.SaleStoreDTO;
import com.bim.inventory.entity.Payment;
import com.bim.inventory.entity.SaleStore;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface PaymentService {


    ResponseEntity<Payment> addPayment(Long storeId, Long newPayment);

    ResponseEntity<Payment> updatePayment(Long storeId, Long paymentId, Long newPayment);

    double calculateTotalPaymentsByStore(Long storeId);

    SaleStoreDTO convertToDTO(SaleStore saleStore);

//    double releasePaidAmount(Long storeId, int fullAmount);


    void deletePayment(Long paymentId);

    ResponseEntity<List<PaymentDTO>> getAllPayments(Long storeId);
}
