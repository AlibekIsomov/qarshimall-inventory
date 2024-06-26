package com.bim.inventory.repository;

import com.bim.inventory.entity.Payment;
import com.bim.inventory.entity.SaleStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment , Long> {
//    List<Payment> findBySaleStore(SaleStore saleStore);

    @Query("SELECT s.initialPayment + COALESCE((SELECT SUM(p.newPayment) FROM Payment p WHERE p.saleStore = :saleStore), 0) FROM SaleStore s WHERE s = :saleStore")
    int calculateTotalPaymentsByStore(@Param("saleStore") SaleStore saleStore);




}

