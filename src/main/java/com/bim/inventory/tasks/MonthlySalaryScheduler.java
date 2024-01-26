package com.bim.inventory.tasks;

import com.bim.inventory.dto.MonthlySalaryDTO;
import com.bim.inventory.service.MonthlySalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MonthlySalaryScheduler {

    @Autowired
    private MonthlySalaryService monthlySalaryService;

    @Scheduled(cron = "0 0 0 1 * ?") // This cron expression triggers the task at midnight on the 1st day of every month
    public void createMonthlySalaryForFirstDay() {
        try {
            MonthlySalaryDTO data = new MonthlySalaryDTO();

            data.setPropertiesForFirstDay();

            monthlySalaryService.create(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
