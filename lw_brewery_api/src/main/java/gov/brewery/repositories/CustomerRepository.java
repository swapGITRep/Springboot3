package gov.brewery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.brewery.entities.Customer;

import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
