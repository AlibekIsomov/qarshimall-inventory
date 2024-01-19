package com.bim.inventory.dto;


import com.bim.inventory.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentStoreDTO {
    private Long id;

    private Long RentingAmount;

    private Long StoreId;

}
