package com.bim.inventory.repository;


import com.bim.inventory.entity.RentStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentStoreRepository extends JpaRepository<RentStore , Long> {


}
