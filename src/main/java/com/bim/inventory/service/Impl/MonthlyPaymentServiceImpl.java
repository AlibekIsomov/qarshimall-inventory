package com.bim.inventory.service.Impl;


import com.bim.inventory.dto.MonthlyPaymentDTO;
import com.bim.inventory.entity.*;
import com.bim.inventory.repository.MonthlyPaymentRepository;
import com.bim.inventory.repository.RentStoreRepository;
import com.bim.inventory.service.MonthlyPaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        PaymentStatus paymentStatus = PaymentStatus.valueOf(data.getStatus());

        MonthlyPayment monthlyPayment = new MonthlyPayment();
        monthlyPayment.setPaymentAmount(data.getPaymentAmount());
        monthlyPayment.setToDate(data.getToDate());
        monthlyPayment.setFromDate(data.getFromDate());
        monthlyPayment.setRentStore(storeOptional.get());
        monthlyPayment.setStatus(paymentStatus);

        return Optional.of(monthlyPaymentRepository.save(monthlyPayment));
    }


    @Override
    public Optional<MonthlyPayment> update(Long id, MonthlyPaymentDTO data) throws Exception {

        Optional<MonthlyPayment> optionalMonthlyPayment = monthlyPaymentRepository.findById(id);

        if (!optionalMonthlyPayment.isPresent()) {
            logger.info("Such ID category does not exist!");
        }
        PaymentStatus paymentStatus = PaymentStatus.valueOf(data.getStatus());

        MonthlyPayment monthlyPayment = optionalMonthlyPayment.get();

        monthlyPayment.setStatus(paymentStatus);
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
    @Override
    public void deletePayment(Long monthlyPaymentId) {
        Optional<MonthlyPayment> paymentOptional = monthlyPaymentRepository.findById(monthlyPaymentId);

        if (paymentOptional.isPresent()) {
            MonthlyPayment paymentToDelete = paymentOptional.get();

            // Remove the payment from the associated store's payments list
            RentStore rentStore = paymentToDelete.getRentStore();
            if (rentStore != null) {
                rentStore.getMonthlyPayments().remove(paymentToDelete);
            }

            // Delete the payment from the database
            monthlyPaymentRepository.delete(paymentToDelete);
        } else {
            // Handle the case where the payment with the given id is not found
            // You can throw an exception, log a message, or handle it in another way.
            // For simplicity, I'll log a message.
            System.out.println("Payment with id " + monthlyPaymentId + " not found");
        }
    }

    @Override
    public ResponseEntity<List<MonthlyPaymentDTO>> getAllPayments(Long rentStoreId){
        Optional<RentStore> storeOptional = rentStoreRepository.findById(rentStoreId);

        if (storeOptional.isPresent()) {
            RentStore rentStore = storeOptional.get();

            List<MonthlyPaymentDTO> paymentDTOs = rentStore.getMonthlyPayments()
                    .stream()
                    .map(this::convertToPaymentDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(paymentDTOs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
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
    private MonthlyPaymentDTO convertToPaymentDTO(MonthlyPayment payments) {
        MonthlyPaymentDTO paymentDTO = new MonthlyPaymentDTO();
        paymentDTO.setId(payments.getId());
        paymentDTO.setPaymentAmount(payments.getPaymentAmount());
        paymentDTO.setToDate(payments.getToDate());
        paymentDTO.setFromDate(payments.getFromDate());
        paymentDTO.setStatus(String.valueOf(payments.getStatus()));
        return paymentDTO;

    }
}
