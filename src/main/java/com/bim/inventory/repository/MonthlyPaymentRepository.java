package com.bim.inventory.repository;

import com.bim.inventory.entity.MonthlyPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MonthlyPaymentRepository extends JpaRepository<MonthlyPayment , Long> {
}
