package gov.brewery.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import gov.brewery.model.CustomerDTO;
import gov.brewery.model.CustomerResponseDTO;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface CustomerService {

    Optional<CustomerDTO> getCustomerById(UUID uuid);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO saveNewCustomer(CustomerDTO customer);

    Optional<CustomerDTO> updateCustomerById(UUID customerId, CustomerDTO customer);

    Boolean deleteCustomerById(UUID customerId);

    Optional<CustomerDTO> patchCustomerById(UUID customerId, CustomerDTO customer);

    List<CustomerResponseDTO> findAllCustomerWhoOrdered();
}
