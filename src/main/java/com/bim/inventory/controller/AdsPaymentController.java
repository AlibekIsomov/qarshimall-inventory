package com.bim.inventory.controller;


import com.bim.inventory.dto.AdsPaymentDTO;
import com.bim.inventory.dto.CategoryDTO;
import com.bim.inventory.dto.MonthlyPaymentDTO;
import com.bim.inventory.entity.AdsPayment;
import com.bim.inventory.entity.Category;
import com.bim.inventory.entity.MonthlyPayment;
import com.bim.inventory.repository.AdsPaymentRepository;
import com.bim.inventory.service.AdsPaymentService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/adsPayment")
public class AdsPaymentController {

    @Autowired
    AdsPaymentService adsPaymentService;

    @PostMapping
    public ResponseEntity<AdsPayment> create(@RequestBody AdsPaymentDTO data) throws Exception {
        try {
            Optional<AdsPayment> createdPayment = adsPaymentService.create(data);

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
    public ResponseEntity<AdsPayment> update(@PathVariable Long id, @RequestBody AdsPaymentDTO data) {
        try {
            Optional<AdsPayment> updatedPayment = adsPaymentService.update(id, data);

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


    @GetMapping("/all/{adsItemId}")
    public ResponseEntity<List<AdsPayment>> getAllPayments(@PathVariable Long adsItemId) {
        return adsPaymentService.getAllPayments(adsItemId);
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<String> deletePayment(@PathVariable Long adsPaymentId) {
        try {
            adsPaymentService.deletePayment(adsPaymentId);
            return new ResponseEntity<>("Payment deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            // Handle exceptions appropriately (e.g., log the error)
            return new ResponseEntity<>("Failed to delete payment: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
