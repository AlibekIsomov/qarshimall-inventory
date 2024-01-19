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

    @OneToOne
    private FileEntity fileEntity;

    @ManyToOne
    @JoinColumn(name = "categoryStore_id", nullable = false)
    private CategoryStore categoryStore;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private Instant createdAt;
}
