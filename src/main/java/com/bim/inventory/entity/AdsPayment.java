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


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class AdsPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false)
    private Long paymentAmount;

    private Long paidAmount;

    @Column(nullable = false)
    private Date toDate;

    @Column(nullable = false)
    private Date fromDate;

    @Column(nullable = false)
    private Long contractNumber;

    @ManyToOne
    @JoinColumn(name = "adsItem_id")
    @JsonBackReference
    private AdsItem adsItem;

    private PaymentStatus status;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private Instant createdAt;
}
