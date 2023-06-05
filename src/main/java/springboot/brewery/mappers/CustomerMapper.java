package springboot.brewery.mappers;

import org.mapstruct.Mapper;

import springboot.brewery.entities.Customer;
import springboot.brewery.model.CustomerDTO;

/**
 * Created by jt, Spring Framework Guru.
 */
@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO dto);

    CustomerDTO customerToCustomerDto(Customer customer);

}
