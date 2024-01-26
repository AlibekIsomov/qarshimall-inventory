package com.bim.inventory.dto;


import com.bim.inventory.entity.Worker;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.jdbc.Work;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlySalaryDTO {

    private LocalDate month;

    private String status;

    private Long paymentAmount;

    private Long paidAmount;

    private Long workerId;

    @JsonIgnore
    private Worker worker;

    public void setPropertiesForFirstDay() {
        // Set the month to the first day of the current month
        this.month = LocalDate.now().withDayOfMonth(1);

        this.status = "PROCESS";
        this.paymentAmount = worker.getCurrentSalary();
        this.paidAmount = 0L;
    }


}
