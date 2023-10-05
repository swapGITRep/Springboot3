package gov.brewery.mappers;

import org.mapstruct.Mapper;

import gov.brewery.entities.Customer;
import gov.brewery.model.CustomerDTO;

/**
 * Created by jt, Spring Framework Guru.
 */
@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO dto);

    CustomerDTO customerToCustomerDto(Customer customer);

}
