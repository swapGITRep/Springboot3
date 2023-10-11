package gov.brewery.repositories;

import gov.brewery.model.CustomerResponseDTO;

import java.util.List;

public interface ICustomerCustomRepository {
     List<CustomerResponseDTO> findAllCustomerWhoOrdered();
}
