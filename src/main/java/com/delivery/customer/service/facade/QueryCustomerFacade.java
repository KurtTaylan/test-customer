package com.delivery.customer.service.facade;

import com.delivery.customer.model.dto.response.CustomerIndividualResponse;
import com.delivery.customer.model.dto.response.CustomerPaginationResponse;
import com.delivery.customer.model.dto.response.CustomerSearchResponse;
import com.delivery.customer.model.entity.Customer;
import com.delivery.customer.service.domain.customer.QueryCustomerService;
import com.delivery.customer.service.mapper.MappingCustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Service
public class QueryCustomerFacade {

    private final QueryCustomerService queryCustomerService;
    private final MappingCustomerService mappingCustomerService;


    public CustomerPaginationResponse paginationAllCustomers(int page, int count) {
        log.info("Retrieve All Customers Request is fetched!: page: {} count: {}", page, count);
        Page<Customer> paginationCustomer = queryCustomerService.paginationOverCustomer(page, count);
        return mappingCustomerService.mapForPagination(paginationCustomer, page);
    }

    public CustomerIndividualResponse retrieveIndividualCustomer(Long id) {
        log.info("Retrieve Individual Customers Request is fetched!: id: {}", id);
        Optional<Customer> optionalCustomer = queryCustomerService.retrieveCustomerById(id);
        return mappingCustomerService.mapForRetrieveIndividual(optionalCustomer);
    }

    public CustomerSearchResponse searchCustomersByCity(String name) {
        log.info("Search All Customers By City Name Request is fetched!: name: {}", name);
        List<Customer> searchCustomer = queryCustomerService.searchCustomersByCity(name);
        return mappingCustomerService.mapForSearch(searchCustomer);
    }

    public CustomerSearchResponse searchCustomersByPhonePrefix(String prefix) {
        log.info("Search All Customers By Phone Prefix Request is fetched!: prefix: {}", prefix);
        List<Customer> searchCustomer = queryCustomerService.searchCustomersByPhonePrefix(prefix);
        return mappingCustomerService.mapForSearch(searchCustomer);
    }
}
