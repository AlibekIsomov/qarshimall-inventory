package com.bim.inventory.controller;


import com.bim.inventory.dto.MonthlyPaymentDTO;
import com.bim.inventory.dto.RentStoreDTO;
import com.bim.inventory.entity.MonthlyPayment;
import com.bim.inventory.entity.RentStore;
import com.bim.inventory.repository.MonthlyPaymentRepository;
import com.bim.inventory.service.MonthlyPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/monthlyPayment")
public class MonthlyPaymentController {


    @Autowired
    MonthlyPaymentService monthlyPaymentService;

    @Autowired
    MonthlyPaymentRepository monthlyPaymentRepository;

    @PostMapping
    public ResponseEntity<MonthlyPayment> create(@RequestBody MonthlyPaymentDTO data) throws Exception {
        try {
            Optional<MonthlyPayment> createdPayment = monthlyPaymentService.create(data);

            if (createdPayment.isPresent()) {
                return ResponseEntity.ok(createdPayment.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MonthlyPayment> update(@PathVariable Long id, @RequestBody MonthlyPaymentDTO data) {
        try {
            Optional<MonthlyPayment> updatedPayment = monthlyPaymentService.update(id, data);

            if (updatedPayment.isPresent()) {
                return ResponseEntity.ok(updatedPayment.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NoSuchElementException storeNotFoundException) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
