package com.bim.inventory.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class MonthlySalary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate month;

    private PaymentStatus status;

    private Long paymentAmount;

    private Long paidAmount;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;

    @OneToMany(mappedBy = "monthlySalary", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<MonthlySalaryPayment> monthlySalaryPayments = new ArrayList<>();

    @CreatedDate
    private Instant createdAt;
}
