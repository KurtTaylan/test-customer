package com.delivery.customer.model.entity;

import com.delivery.customer.model.enumtype.AddressEnumType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@ToString(exclude = "customer")
@EqualsAndHashCode(exclude = {"customer"}, callSuper = false)
@Data
@Entity(name = "address")
public class Address extends BaseEntity {

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private AddressEnumType type;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "line", nullable = false, length = 510)
    private String line;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}
