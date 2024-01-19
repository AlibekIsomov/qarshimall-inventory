package com.bim.inventory.service;

import com.bim.inventory.dto.MonthlyPaymentDTO;
import com.bim.inventory.entity.MonthlyPayment;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface MonthlyPaymentService {

    Optional<MonthlyPayment> create(MonthlyPaymentDTO data) throws Exception;

    Optional<MonthlyPayment> update(Long id, MonthlyPaymentDTO data) throws Exception;
}
