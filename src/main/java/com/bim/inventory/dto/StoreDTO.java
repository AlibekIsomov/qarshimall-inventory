package com.bim.inventory.dto;

import com.bim.inventory.entity.Category;
import com.bim.inventory.entity.RentStore;
import com.bim.inventory.entity.SaleStore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO {
    private Long id;

    private int contractNumber;

    private String FullName;

    private int storeNumber;

    private double size;

    private Long categoryStoreId;
}
