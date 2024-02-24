package com.bim.inventory.repository;


import com.bim.inventory.entity.AdsPayment;
import com.bim.inventory.entity.MonthlyPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdsPaymentRepository extends JpaRepository<AdsPayment, Long> {

    List<AdsPayment> findByAdsItemId(Long adsItemId);
}
