package com.bim.inventory.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleStoreDTO {
    private Long id;

    private Long fullAmount;

    private int contractNumber;

    private String fullName;

    private int storeNumber;

    private Long initialPayment;

    private double size;

    private Long lastPayment;

    private Long StoreId;

    private List<PaymentDTO> payments;

}
