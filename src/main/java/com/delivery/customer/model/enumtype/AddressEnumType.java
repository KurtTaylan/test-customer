package com.delivery.customer.model.enumtype;

import lombok.Getter;

@Getter
public enum AddressEnumType {

    BUSINESS(1),
    HOME(2);

    private final int value;

    AddressEnumType(int value) {
        this.value = value;
    }
}
