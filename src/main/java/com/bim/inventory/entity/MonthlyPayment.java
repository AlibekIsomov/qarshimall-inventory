package com.bim.inventory.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class MonthlyPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long paymentAmount;

    private Long paidAmount;

    @Column(nullable = false)
    private Date toDate;

    @Column(nullable = false)
    private Date fromDate;

    @ManyToOne
    @JoinColumn(name = "rentStore_id")
    @JsonBackReference
    private RentStore rentStore;

    
    private PaymentStatus status;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private Instant createdAt;
}
