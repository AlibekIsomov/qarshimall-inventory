package com.bim.inventory.service;

import com.bim.inventory.dto.SalaryDTO;
import com.bim.inventory.dto.WorkerDTO;
import com.bim.inventory.entity.Payment;
import com.bim.inventory.entity.Salary;
import com.bim.inventory.entity.Worker;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SalaryService {


     ResponseEntity<Salary> addSalary(Long workerId, Long newSalary);

    ResponseEntity<Salary> updateSalary(Long workerId, Long salaryId, Long newSalary);

    void deleteSalary(Long salaryId);

    ResponseEntity<List<SalaryDTO>> getAllSalary(Long workerId);
}
