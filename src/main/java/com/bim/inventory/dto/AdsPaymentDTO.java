package com.bim.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdsPaymentDTO {

    private String clientName;

    private Long paymentAmount;

    private Long paidAmount;

    private Date toDate;

    private Date fromDate;

    private Long adsItemId;

    private Long contractNumber;

    private String status;
}
