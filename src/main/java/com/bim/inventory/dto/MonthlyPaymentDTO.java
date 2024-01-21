package com.bim.inventory.dto;


import com.bim.inventory.entity.RentStore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyPaymentDTO {

    private Long Id;

    private Long paymentAmount;

    private Long paidAmount;

    private Date toDate;

    private Date fromDate;

    private String status;

    private Long rentStoreId;

    private Instant createdAt;
}
