package com.bim.inventory.repository;


import com.bim.inventory.entity.MonthlySalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlySalaryRepository extends JpaRepository<MonthlySalary, Long> {
    Iterable<? extends MonthlySalary> findAllByWorkerId(Long id);
}
