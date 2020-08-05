package com.delivery.customer.model.entity;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "create_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updatedAt;
}
