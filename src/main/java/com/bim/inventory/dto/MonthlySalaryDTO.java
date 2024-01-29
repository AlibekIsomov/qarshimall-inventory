package com.bim.inventory.dto;


import com.bim.inventory.entity.Worker;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.jdbc.Work;

import java.time.Instant;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlySalaryDTO {

    private Long id;

    private Instant month;

    private String status;

    private Long paymentAmount;

    private Long paidAmount;

    private Long workerId;

    private Instant createdAt;


    @JsonIgnore
    private Worker worker;

    public void setPropertiesForFirstDay() {



        this.month = Instant.from(LocalDate.now().withDayOfMonth(1));

        this.status = "PROCESS";
        this.paymentAmount = worker.getCurrentSalary();
        this.paidAmount = 0L;
    }



}
