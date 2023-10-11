package gov.brewery.repositories.impl;

import com.querydsl.jpa.JPQLQueryFactory;
import gov.brewery.entities.QBeerOrder;
import gov.brewery.entities.QCustomer;
import gov.brewery.model.CustomerResponseDTO;
import gov.brewery.repositories.ICustomerCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
public class CustomerCustomRepositoryImpl implements ICustomerCustomRepository {

    @Autowired
    private JPQLQueryFactory queryFactory;

    @Override
    public List<CustomerResponseDTO> findAllCustomerWhoOrdered(){
        QCustomer customer = QCustomer.customer;
        QBeerOrder beerOrder = QBeerOrder.beerOrder;

        return queryFactory.select(customer.id, customer.name, customer.email, beerOrder.id)
                .from(customer)
                .innerJoin(beerOrder)
                .on(customer.id.eq(beerOrder.customer.id))
                .fetch()
                .stream()
                .map(tuple -> CustomerResponseDTO.builder()
                        .id(tuple.get(customer.id))
                        .name(tuple.get(customer.name))
                        .email(tuple.get(customer.email))
                        .beerOrderId(tuple.get(beerOrder.id))
                        .build()
                )
                .collect(Collectors.toList());
    }
}
