package com.bim.inventory.service.Impl;


import com.bim.inventory.dto.MonthlyPaymentDTO;
import com.bim.inventory.entity.*;
import com.bim.inventory.repository.MonthlyPaymentRepository;
import com.bim.inventory.repository.RentStoreRepository;
import com.bim.inventory.service.MonthlyPaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MonthlyPaymentServiceImpl implements MonthlyPaymentService {

    private static final Logger logger = LoggerFactory.getLogger(MonthlyPaymentServiceImpl.class);

    @Autowired
    MonthlyPaymentRepository monthlyPaymentRepository;

    @Autowired
    RentStoreRepository rentStoreRepository;


    @Override
    public Optional<MonthlyPayment> create(MonthlyPaymentDTO data) throws Exception {

        Optional<RentStore> storeOptional = rentStoreRepository.findById(data.getRentStoreId());

        if (!storeOptional.isPresent()) {
            logger.info("Such ID category does not exist!");
        }

        MonthlyPayment monthlyPayment = new MonthlyPayment();
        monthlyPayment.setPaymentAmount(data.getPaymentAmount());
        monthlyPayment.setToDate(data.getToDate());
        monthlyPayment.setFromDate(data.getFromDate());
        monthlyPayment.setRentStore(storeOptional.get());

        return Optional.of(monthlyPaymentRepository.save(monthlyPayment));
    }


    @Override
    public Optional<MonthlyPayment> update(Long id, MonthlyPaymentDTO data) throws Exception {

        Optional<MonthlyPayment> optionalMonthlyPayment = monthlyPaymentRepository.findById(id);

        if (!optionalMonthlyPayment.isPresent()) {
            logger.info("Such ID category does not exist!");
        }

        MonthlyPayment monthlyPayment = optionalMonthlyPayment.get();

        monthlyPayment.setPaidAmount(data.getPaidAmount());



        return Optional.of(monthlyPaymentRepository.save(monthlyPayment));
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
//    @Override
//    public void deletePayment(Long paymentId) {
//        Optional<Payment> paymentOptional = paymentRepository.findById(paymentId);
//
//        if (paymentOptional.isPresent()) {
//            Payment paymentToDelete = paymentOptional.get();
//
//            // Remove the payment from the associated store's payments list
//            SaleStore saleStore = paymentToDelete.getSaleStore();
//            if (saleStore != null) {
//                saleStore.getPayments().remove(paymentToDelete);
//            }
//
//            // Delete the payment from the database
//            paymentRepository.delete(paymentToDelete);
//        } else {
//            // Handle the case where the payment with the given id is not found
//            // You can throw an exception, log a message, or handle it in another way.
//            // For simplicity, I'll log a message.
//            System.out.println("Payment with id " + paymentId + " not found");
//        }
//    }
//
//    @Override
//    public ResponseEntity<List<PaymentDTO>> getAllPayments (Long saleStoreId){
//        Optional<SaleStore> storeOptional = storeRepository.findById(saleStoreId);
//
//        if (storeOptional.isPresent()) {
//            SaleStore saleStore = storeOptional.get();
//
//            List<PaymentDTO> paymentDTOs = saleStore.getPayments()
//                    .stream()
//                    .map(this::convertToPaymentDTO)
//                    .collect(Collectors.toList());
//
//            return ResponseEntity.ok(paymentDTOs);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//    @Override
//    public SaleStoreDTO convertToDTO(SaleStore saleStore) {
//        SaleStoreDTO saleStoreDTO = new SaleStoreDTO();
//        saleStoreDTO.setId(saleStore.getId());
//        saleStoreDTO.setFullAmount(saleStore.getFullAmount());
//
//        List<PaymentDTO> paymentDTOs = saleStore.getPayments()
//                .stream()
//                .map(this::convertToPaymentDTO)
//                .collect(Collectors.toList());
//        saleStoreDTO.setPayments(paymentDTOs);
//
//        return saleStoreDTO;
//    }
//
//
//    private PaymentDTO convertToPaymentDTO(Payment payments) {
//        PaymentDTO paymentDTO = new PaymentDTO();
//        paymentDTO.setId(payments.getId());
//        paymentDTO.setNewPayment(payments.getNewPayment());
//        paymentDTO.setCreatedAt(payments.getCreatedAt());
//        return paymentDTO;
//
//    }
}
