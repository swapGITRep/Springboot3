package gov.brewery.repositories;

import gov.brewery.entities.Beer;
import gov.brewery.model.BeerDTO;

import java.util.List;

public interface IBeerCustomRepository {
    Iterable<Beer> findAllBeersByQuantityOnHand(Integer quantityOnHand);
}
