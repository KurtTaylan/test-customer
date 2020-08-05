package com.delivery.customer.service.repository;

import com.delivery.customer.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByCustomerIdAndCountryAndCityAndLine(Long customerId, String country, String city, String line);
}
