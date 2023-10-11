package gov.brewery.repositories;

import gov.brewery.entities.BeerOrder;
import gov.brewery.entities.BeerOrderShipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerOrderShipmentRepository extends JpaRepository<BeerOrderShipment, UUID> {
}
