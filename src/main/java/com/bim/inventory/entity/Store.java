package com.bim.inventory.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private int contractNumber;

    @Column(nullable = false)
    private String FullName;

    @Column(nullable = false, unique = true)
    private int storeNumber;

    @Column(nullable = false)
    private double size;

    @ManyToOne
    @JoinColumn(name = "saleStore_id")
    private SaleStore saleStore;

    @ManyToOne
    @JoinColumn(name = "rentStore_id")
    private RentStore rentStore;

    @ManyToOne
    private Category category;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private Instant createdAt;
}
