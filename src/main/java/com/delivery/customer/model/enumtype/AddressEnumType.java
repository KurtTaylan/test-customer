package com.delivery.customer.model.enumtype;

import lombok.Getter;

@Getter
public enum AddressEnumType {

    business(1),
    home(2);

    private final int value;

    AddressEnumType(int value) {
        this.value = value;
    }
}
