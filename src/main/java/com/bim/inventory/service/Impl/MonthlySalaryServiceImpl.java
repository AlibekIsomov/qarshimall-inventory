package com.bim.inventory.service.Impl;


import com.bim.inventory.dto.MonthlySalaryDTO;
import com.bim.inventory.entity.MonthlySalary;
import com.bim.inventory.entity.MonthlySalaryPayment;
import com.bim.inventory.entity.PaymentStatus;
import com.bim.inventory.entity.Worker;
import com.bim.inventory.repository.MonthlySalaryPaymentRepository;
import com.bim.inventory.repository.MonthlySalaryRepository;
import com.bim.inventory.repository.WorkerRepository;
import com.bim.inventory.service.MonthlySalaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonthlySalaryServiceImpl implements MonthlySalaryService {
    private static final Logger logger = LoggerFactory.getLogger(MonthlySalaryServiceImpl.class);

    @Autowired
    MonthlySalaryRepository monthlySalaryRepository;

    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    MonthlySalaryPaymentRepository monthlySalaryPaymentRepository;


    @Override
    public Optional<MonthlySalary> create( MonthlySalaryDTO data) throws Exception {

        Optional<Worker> workerOptional = workerRepository.findById(data.getWorkerId());

        if (workerOptional.isPresent()) {
            Worker worker = workerOptional.get();



            MonthlySalary monthlySalary = new MonthlySalary();

            monthlySalary.setMonth(data.getMonth());
            monthlySalary.setStatus(PaymentStatus.valueOf(data.getStatus()));
            monthlySalary.setPaymentAmount(data.getPaymentAmount());
            monthlySalary.setPaidAmount(data.getPaidAmount());
            monthlySalary.setWorker(workerOptional.get());

            worker.setCurrentSalary(data.getPaymentAmount());

            return Optional.of(monthlySalary);
        } else {
            return Optional.empty();
        }
        }



    @Override
    public Optional<MonthlySalary> update(Long id, MonthlySalaryDTO data) throws Exception {

        Optional<MonthlySalary> optionalMonthlySalary = monthlySalaryRepository.findById(id);

        if (!optionalMonthlySalary.isPresent()) {
            logger.info("Such ID MonthlySalaryPayments does not exist!");
        }

        Optional<Worker> workerOptional = workerRepository.findById(data.getWorkerId());

        if (workerOptional.isPresent()) {
            Worker worker = workerOptional.get();



            MonthlySalary monthlySalary = new MonthlySalary();

            monthlySalary.setMonth(data.getMonth());
            monthlySalary.setStatus(PaymentStatus.valueOf(data.getStatus()));
            monthlySalary.setPaymentAmount(data.getPaymentAmount());
            monthlySalary.setPaidAmount(data.getPaidAmount());
            monthlySalary.setWorker(workerOptional.get());

            worker.setCurrentSalary(data.getPaymentAmount());
            workerRepository.save(worker);

            return Optional.of(monthlySalary);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(Long id) {
        if(!monthlySalaryRepository.existsById(id)) {
            logger.info("Store with id " + id + " does not exists");
        }

        List<MonthlySalaryPayment> paymentsToDelete = monthlySalaryPaymentRepository.findAllByMonthlySalaryId(id);
        monthlySalaryPaymentRepository.deleteAll(paymentsToDelete);

        monthlySalaryRepository.deleteById(id);
    }

    @Override
    public Page<MonthlySalary> getAll(Pageable pageable) throws Exception {
        return monthlySalaryRepository.findAll(pageable);
    }



    @Override
    public Optional<MonthlySalary> getById(Long id) throws Exception {
        if (!monthlySalaryRepository.existsById(id)) {
            logger.info("Input with id " + id + " does not exists");
            return Optional.empty();
        }
        return monthlySalaryRepository.findById(id);
    }
}
